package tads;

import java.util.Comparator;

public class Lista<T> {
    private NoLista<T> head;
    private NoLista<T> tail;
    private int tamanho;

    public Lista() {
        head = null;
        tail = null;
        tamanho = 0;
    }

    public boolean adicionar(int pos, T valor) {
        if (pos < 0 || pos > tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida: " + pos);
        }

        NoLista<T> novo = new NoLista<>(valor);

        if (pos == 0) {
            if (head == null) {
                head = novo;
                tail = novo;
            } else {
                novo.setProx(head);
                head.setAnt(novo);
                head = novo;
            }
        } else if (pos == tamanho) {
            tail.setProx(novo);
            novo.setAnt(tail);
            tail = novo;
        } else {
            NoLista<T> atual = head;
            for (int i = 0; i < pos - 1; i++) {
                atual = atual.getProx();
            }
            novo.setProx(atual.getProx());
            atual.setProx(novo);
            novo.getProx().setAnt(novo);
            novo.setAnt(atual);
        }
        tamanho++;
        return true;
    }

    public void adicionarOrdenado(T elemento, Comparator<T> comparador) {
        if (elemento == null || comparador == null) {
            throw new IllegalArgumentException("Elemento e comparador não podem ser nulos");
        }

        if (head == null || comparador.compare(elemento, head.getValor()) <= 0) {
            adicionar(0, elemento);
            return;
        }

        if (comparador.compare(elemento, tail.getValor()) >= 0) {
            adicionar(tamanho, elemento);
            return;
        }

        NoLista<T> atual = head;
        int pos = 0;
        while (atual != null && comparador.compare(elemento, atual.getValor()) > 0) {
            atual = atual.getProx();
            pos++;
        }

        adicionar(pos, elemento);
    }

    public T removerHead() {
        if (head == null) {
            return null;
        }
        T valor = head.getValor();
        head = head.getProx();
        if (head != null) {
            head.setAnt(null);
        } else {
            tail = null;
        }
        tamanho--;
        return valor;
    }

    public boolean remover(int pos) {
        if (pos < 0 || pos >= tamanho || head == null) {
            throw new IndexOutOfBoundsException("Posição inválida: " + pos);
        }

        if (tamanho == 1) {
            head = null;
            tail = null;
        } else if (pos == 0) {
            head = head.getProx();
            head.setAnt(null);
        } else if (pos == tamanho - 1) {
            tail = tail.getAnt();
            tail.setProx(null);
        } else {
            NoLista<T> atual = getNo(pos);
            atual.getAnt().setProx(atual.getProx());
            atual.getProx().setAnt(atual.getAnt());
        }
        tamanho--;
        return true;
    }

    private NoLista<T> getNo(int pos) {
        if (pos < 0 || pos >= tamanho) {
            return null;
        }

        NoLista<T> atual;
        if (pos < tamanho / 2) {
            atual = head;
            for (int i = 0; i < pos; i++) {
                atual = atual.getProx();
            }
        } else {
            atual = tail;
            for (int i = tamanho - 1; i > pos; i--) {
                atual = atual.getAnt();
            }
        }
        return atual;
    }

    public NoLista<T> espiarPrimeiro() {
        return head;
    }

    public int getTamanho() {
        return tamanho;
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public void imprimir() {
        NoLista<T> atual = head;
        while (atual != null) {
            System.out.print(atual.getValor() + " ");
            atual = atual.getProx();
        }
        System.out.println("-> NULL");
    }

    public void imprimirReverso() {
        NoLista<T> atual = tail;
        while (atual != null) {
            System.out.print(atual.getValor() + " ");
            atual = atual.getAnt();
        }
        System.out.println("-> NULL");
    }
}
