package eventos;

import caminhoes.CaminhaoPequeno;
import configsimulador.ConfiguracoesDoSimulador;
import timer.Timer;
import zonas.Zonas;

import java.sql.Time;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Evento responsável por realizar a coleta de lixo em uma região específica utilizando um caminhão pequeno.
 * A coleta ocorre enquanto houver lixo na região e o caminhão puder realizar novas viagens.
 * Ao término da coleta, agenda a próxima coleta ou a transferência para a estação.
 */

/**
 * Representa um evento de coleta de lixo em uma zona específica.
 * Utiliza um caminhão pequeno para realizar a coleta até atingir a capacidade máxima
 * ou até não haver mais lixo disponível na zona.
 * <p>
 * Caso o caminhão ainda possa realizar viagens após a coleta, uma nova coleta é agendada.
 * Caso contrário, agenda-se a transferência do lixo para a estação de transferência.
 */
public class ColetaLixo extends Evento {

    /**
     * Caminhão utilizado na coleta.
     */
    private CaminhaoPequeno caminhao;

    /**
     * Zona onde será realizada a coleta.
     */
    private Zonas zona;

    /**
     * Construtor do evento de coleta de lixo.
     *
     * @param tempo    Tempo em que o evento ocorre.
     * @param caminhao Caminhão que realizará a coleta.
     * @param zona     Zona de onde o lixo será coletado.
     */
    public ColetaLixo(int tempo, CaminhaoPequeno caminhao, Zonas zona) {
        super(tempo);
        this.caminhao = caminhao;
        this.zona = zona;
    }

    /**
     * Executa o evento de coleta:
     * <ul>
     *     <li>Verifica se há lixo na zona.</li>
     *     <li>Enquanto possível, coleta lixo do local para o caminhão.</li>
     *     <li>Agenda nova coleta ou transferência conforme regras de viagem.</li>
     * </ul>
     */
    @Override
    public void executar() {

        // Verifica se há lixo acumulado na zona
        int qtdZona = zona.getLixoAcumulado();
        if (qtdZona == 0) {
            System.out.println("  • Zona está limpa. Nenhuma coleta realizada.");
            return;
        }

        boolean coletou = false;
        int totalColetado = 0;

        // Loop de coleta até atingir limite do caminhão ou esgotar lixo da zona
        while (caminhao.podeViajarNovamente() &&
                caminhao.getCargaAtual() < caminhao.getCapacidadeMaxima() &&
                zona.getLixoAcumulado() > 0) {

            int qtdDisponivelZona = zona.getLixoAcumulado();
            int espacoRestante = caminhao.getCapacidadeMaxima() - caminhao.getCargaAtual();
            int qtdReal = Math.min(qtdDisponivelZona, espacoRestante);

            String horarioAtual = Timer.formatarHorarioSimulado(tempo);
            System.out.println("======================= C O L E T A =======================");
            System.out.printf("[%s] \n", horarioAtual);
            System.out.printf("[COLETA] Caminhão %s → Zona %s | %s Viagens %n", caminhao.getId(), zona.getNome(), caminhao.getViagensRestantes());

            coletou = caminhao.coletarCarga(qtdReal);
            if (coletou) {
                zona.coletarLixo(qtdReal);
                totalColetado += qtdReal;
                System.out.printf("  • Coletou: %dt    Carga: %d/%d%n",
                        qtdReal, caminhao.getCargaAtual(), caminhao.getCapacidadeMaxima());
            } else {
                System.out.println("  • Carga máxima atingida.");
                break;
            }
        }

        // Após a coleta, decide o próximo evento: nova coleta ou transferência
        if (caminhao.podeViajarNovamente() && coletou) {

            // Calcula tempo de coleta com base na quantidade coletada
            int tempoColeta = totalColetado * ConfiguracoesDoSimulador.TEMPO_COLETA_TONELADA;

            // Define intervalo de tempo de deslocamento com base no horário
            boolean pico = ConfiguracoesDoSimulador.isHorarioDePico(tempo);
            int min = pico ? ConfiguracoesDoSimulador.VIAGEM_MIN_PICO : ConfiguracoesDoSimulador.VIAGEM_MIN_FORA_PICO;
            int max = pico ? ConfiguracoesDoSimulador.VIAGEM_MAX_PICO : ConfiguracoesDoSimulador.VIAGEM_MAX_FORA_PICO;
            int tempoBase = ThreadLocalRandom.current().nextInt(min, max + 1);

            // Calcula tempo real considerando fatores variáveis
            int tempoDeslocamento = Timer.calcularTempoRealDeViagem(tempo, tempoBase);

            // (Opcional) Tempo extra se estiver carregado — não utilizado nesta versão
            int tempoExtraCarregado = 0;

            int tempoTotal = tempoColeta + tempoDeslocamento + tempoExtraCarregado;

            // Informações de log
            String horario = Timer.formatarHorarioSimulado(tempo + tempoTotal);
            String duracao = Timer.formatarDuracao(tempoTotal);

            System.out.printf("  • Tempo de coleta: %s%n", Timer.formatarDuracao(tempoColeta));
            System.out.printf("  • Tempo de trajeto: %s%n", Timer.formatarDuracao(tempoDeslocamento));
            if (tempoExtraCarregado > 0)
                System.out.printf("  • Carga cheia: +%s%n", Timer.formatarDuracao(tempoExtraCarregado));

            System.out.printf("  • Horário: %s    Tempo total: %s%n", horario, duracao);

            // Agenda nova coleta
            GerenciadorAgenda.adicionarEvento(new ColetaLixo(tempo + tempoTotal, caminhao, zona));

        } else {
            // Encaminha caminhão para estação de transferência
            System.out.println("===========================================================");
            int tTransfer = tempo + 1;
            String hTransfer = Timer.formatarHorarioSimulado(tTransfer);
            System.out.printf("[%s] \n", hTransfer);
            System.out.printf("[TRANSFERÊNCIA] Caminhão %s → Estação de Transferência%n",
                    caminhao.getId());

            GerenciadorAgenda.adicionarEvento(new TransferenciaParaEstacao(tTransfer, caminhao));
        }
    }
}