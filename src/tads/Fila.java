package tads;

/**
 * Classe que representa uma fila genérica baseada em nós duplamente ligados.
 *
 * @param <T> o tipo de elemento armazenado na fila
 */
public class Fila<T> {
    private NoFila<T> head;
    private NoFila<T> tail;
    private int tamanho;

    /**
     * Construtor da fila. Inicializa uma fila vazia.
     */
    public Fila() {
        this.head = null;
        this.tail = null;
        this.tamanho = 0;
    }

    /**
     * Insere um novo elemento no início da fila.
     *
     * @param valor o valor a ser inserido
     * @return true se o elemento for inserido com sucesso
     */
    public boolean enqueue(T valor) {
        NoFila<T> novo = new NoFila<>(valor);
        if (tamanho == 0) {
            this.head = novo;
            this.tail = novo;
        } else {
            novo.setProx(this.head);
            this.head = novo;
        }
        tamanho++;
        return true;
    }

    /**
     * Remove o último elemento da fila (FIFO).
     *
     * @return o nó removido ou null se a fila estiver vazia
     */
    public NoFila<T> dequeue() {
        if (tamanho == 0) {
            System.out.println("Não há elementos na fila");
            return null;
        }

        NoFila<T> elemento;

        if (this.head == this.tail) {
            elemento = this.head;
            this.head = null;
            this.tail = null;
        } else {
            NoFila<T> atual = this.head;
            while (atual.getProx() != this.tail) {
                atual = atual.getProx();
            }

            elemento = atual.getProx();
            this.tail = atual;
            atual.setProx(null);
        }

        tamanho--;
        return elemento;
    }

    /**
     * Imprime os elementos da fila na ordem de inserção.
     */
    public void imprimir() {
        NoFila<T> atual = this.head;
        for (int i = this.tamanho; i > 0; i--) {
            if (i == 1) {
                System.out.print(atual.getValor());
            } else {
                System.out.print(atual.getValor() + " -> ");
                atual = atual.getProx();
            }
        }
        System.out.println();
    }

    /**
     * Retorna o tamanho atual da fila.
     *
     * @return o número de elementos na fila
     */
    public int getTamanho() {
        return tamanho;
    }

    /**
     * Verifica se a fila está vazia.
     *
     * @return true se estiver vazia, false caso contrário
     */
    public boolean estaVazia() {
        return tamanho == 0;
    }
}

