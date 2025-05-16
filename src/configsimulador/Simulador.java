package configsimulador;

import caminhoes.CaminhaoGrande;
import caminhoes.CaminhaoPequeno;
import configsimulador.ConfiguracoesDoSimulador;
import estacoes.EstacaoDeTransferencia;
import eventos.GerenciadorAgenda;
import eventos.ColetaLixo;
import zonas.Zonas;


public class Simulador {

    CaminhaoGrande[] caminhoesGrandes;
    CaminhaoPequeno[] caminhaoPequenos;
    EstacaoDeTransferencia[] estacaoDeTransferencias;

    float tempoSimulacao;
    boolean rodando;

    public Simulador() {
        this.tempoSimulacao = tempoSimulacao;
        this.rodando = false;
    }

    public void iniciar() {
        ConfiguracoesDoSimulador leste = Zonas.zonaLeste();
        System.out.println(" SIMULAÇÃO INICIADA ........  ");
        System.out.println();
        leste.gerarLixoDiario();
        System.out.println();


    }

    public void pausar() {
        System.out.println("Simulação pausada.");
        pausado = true;
    }

    public void continuarSimulacao() {
        System.out.println("Simulação retomada.");
        pausado = false;
    }

    public void encerrar() {

    }

    public void gravar(String caminho) {

    }

    public static Simulador carregar(String caminho) {

    }

    private void atualizarSimulacao() {

    }
}