package caminhoes;

public class CaminhaoGrande {
    /**
     * Identificador único do caminhão
     */
    public int id;

    /**
     * Quantidade de viagens realizadas pelo caminhão
     */
    public int totalViagens;

    /**
     * Indica se o caminhão está atualmente em viagem
     */
    public boolean emViagem;

    /**
     * Capacidade máxima de carga do caminhão (em kg)
     */
    protected int capacidadeMaxima = 20000;

    /**
     * Quantidade atual de carga no caminhão (em kg)
     */
    protected int cargaAtual;

    /**
     * Indica se o caminhão está no processo de carregamento
     */
    public boolean emCarregamento;

    /**
     * Construtor padrão. Inicializa o caminhão com carga zero.
     */
    public CaminhaoGrande() {
        this.cargaAtual = 0;
    }

    /**
     * Verifica se o caminhão está pronto para partir com base na carga.
     *
     * @return true se a carga atual for maior ou igual à capacidade máxima
     */
    public boolean prontoParaPartida() {
        return cargaAtual >= capacidadeMaxima;
    }

    /**
     * Adiciona carga ao caminhão.
     *
     * @param quantidade quantidade de carga a ser adicionada (em kg)
     */
    public void adicionarCarga(int quantidade) {
        cargaAtual += quantidade;
    }

    /**
     * Realiza a descarga do caminhão e simula a partida para o aterro.
     */
    public void descarregar() {
        System.out.println("Caminhão grande partiu para o aterro com " + cargaAtual + "kg.");
        cargaAtual = 0;

        // Lógica de espera e decisão sobre descarregar pode ser adicionada aqui futuramente
        /*
         * - Tem tolerância de Espera
         * - Se for excedida, parte para o aterro sanitário
         * - Se estiver vazio, aguarda até ser carregado
         */
    }

    /**
     * Simula a espera pelo carregamento do caminhão.
     *
     * @return true se o caminhão ainda está aguardando carregamento
     */
    public boolean aguardandoCarregamento() {
        return true;
    }
}
