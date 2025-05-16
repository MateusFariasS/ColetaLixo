package eventos;

import caminhoes.CaminhaoPequeno;
import estacoes.EstacaoDeTransferencia;
import configsimulador.ConfiguracoesDoSimulador;
import timer.Timer;

/**
 * Evento responsável por representar a chegada de um caminhão à estação de transferência.
 * Nesse evento, o caminhão descarrega sua carga de lixo e registra a viagem realizada.
 */
public class TransferenciaParaEstacao extends Evento {

    /** Caminhão que está sendo transferido para a estação. */
    private CaminhaoPequeno caminhao;

    /**
     * Construtor do evento de transferência para a estação.
     *
     * @param tempo    O tempo simulado em que o evento ocorre.
     * @param caminhao O caminhão que será transferido para a estação.
     */
    public TransferenciaParaEstacao(int tempo, CaminhaoPequeno caminhao) {
        super(tempo);
        this.caminhao = caminhao;
    }

    /**
     * Executa o evento de transferência:
     * <ul>
     *     <li>Exibe a chegada do caminhão à estação.</li>
     *     <li>Realiza a descarga da carga transportada.</li>
     *     <li>Registra a viagem realizada.</li>
     *     <li>Informa a estação de transferência sobre a carga recebida.</li>
     * </ul>
     */
    @Override
    public void executar() {
        System.out.println("[CAMINHÃO " + caminhao.getId() + "] chegando na estação de transferência.");

        int cargaTransferida = caminhao.descarregarCarga();  // Descarrega o lixo
        caminhao.registrarViagem();                          // Registra viagem após descarga

        System.out.println();
        EstacaoDeTransferencia.receberCarga(caminhao, cargaTransferida); // Estação recebe a carga
    }
}

