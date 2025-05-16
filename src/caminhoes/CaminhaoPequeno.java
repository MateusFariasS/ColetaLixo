package caminhoes;

public class CaminhaoPequeno {
    /**
     * Identificador do caminhão
     */
    private String id;

    /**
     * Capacidade máxima de carga (em toneladas)
     */
    private int capacidadeMaxima;

    /**
     * Quantidade atual de carga (em toneladas)
     */
    private int cargaAtual;

    /**
     * Número de viagens restantes no dia
     */
    private int viagensRestantes;

    /**
     * Indica se o caminhão está em viagem
     */
    private boolean emViagem;

    /**
     * Construtor do caminhão pequeno.
     *
     * @param id                     identificador do caminhão
     * @param capacidadeMaxima       capacidade de carga em toneladas
     * @param viagensRestantes número máximo de viagens que o caminhão pode realizar por dia
     */
    public CaminhaoPequeno(String id, int capacidadeMaxima, int viagensRestantes) {
        this.id = id;
        this.capacidadeMaxima = capacidadeMaxima;
        this.cargaAtual = 0;
        this.viagensRestantes = viagensRestantes;
        this.emViagem = false;
    }

    /**
     * Retorna a capacidade máxima do caminhão.
     *
     * @return capacidade máxima em toneladas
     */
    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    /**
     * Retorna o número de viagens restantes no dia.
     *
     * @return número de viagens restantes
     */
    public int getViagensRestantes() {
        return viagensRestantes;
    }

    /**
     * Retorna o identificador do caminhão.
     *
     * @return ID do caminhão
     */
    public String getId() {
        return id;
    }

    /**
     * Retorna a carga atual no caminhão.
     *
     * @return carga atual em toneladas
     */
    public int getCargaAtual() {
        return cargaAtual;
    }

    public boolean isEmViagem() {
        return emViagem;
    }

    /**
     * Tenta adicionar uma quantidade de carga ao caminhão.
     *
     * @param quantidade quantidade a ser coletada (em toneladas)
     * @return true se a carga foi adicionada com sucesso, false se exceder a capacidade
     */
    public boolean coletarCarga(int quantidade) {
        if (cargaAtual + quantidade <= capacidadeMaxima) {
            cargaAtual += quantidade;
            System.out.println("[CAMINHÃO " + id + "] Coletou " + quantidade + " toneladas");
            return true;
        }
        System.out.println("[CAMINHÃO " + id + "] Carga máxima atingida.");
        return false;
    }

    /**
     * Descarrega todo o conteúdo do caminhão.
     *
     * @return quantidade descarregada (em toneladas)
     */
    public int descarregarCarga() {
        int carga = cargaAtual;
        cargaAtual = 0;
        System.out.println("[CAMINHÃO " + id + "] Descarregou " + carga + " toneladas.");
        return carga;
    }

    /**
     * Verifica se o caminhão ainda pode realizar mais viagens no dia.
     *
     * @return true se houver viagens restantes, false caso contrário
     */
    public boolean podeViajarNovamente() {
        return viagensRestantes > 0;
    }

    /**
     * Registra a realização de uma viagem, decrementando o número de viagens restantes.
     */
    public void registrarViagem() {
        if (viagensRestantes > 0) {
            viagensRestantes--;
            System.out.println("[CAMINHÃO " + id + "] " + viagensRestantes + " viagens restantes.");
        }
    }

}
