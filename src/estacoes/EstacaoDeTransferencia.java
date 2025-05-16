package estacoes;

import caminhoes.CaminhaoPequeno;
import tads.Lista;  // supondo que sua Lista está no pacote 'tads'

public class EstacaoDeTransferencia {
    private static Lista<CaminhaoPequeno> filaCaminhoes = new Lista<>();
    private static int cargaAcumulada = 0;

    /**
     * Recebe carga de um caminhão pequeno e adiciona à fila.
     * Atualiza a carga acumulada na estação.
     *
     * @param caminhao Caminhão que entrega a carga
     * @param carga    Quantidade de carga em toneladas
     */
    public static void receberCarga(CaminhaoPequeno caminhao, int carga) {
        cargaAcumulada += carga;
        filaCaminhoes.adicionar(filaCaminhoes.getTamanho(), caminhao);  // adiciona no final da lista

        System.out.println("Estação recebeu " + carga + " toneladas. Total acumulado: " + cargaAcumulada + " toneladas.");

        // Exemplo: quando atinge 20 toneladas, podemos agendar o carregamento do caminhão grande
        if (cargaAcumulada >= 20) {
            System.out.println("Carga suficiente para caminhão grande. Pode-se agendar EventoCarregamentoCaminhaoGrande.");
        }
    }
}
