package io.rerum.accorgol.model;

/**
 * Created by osman on 28/11/2017.
 */

public class Carreira {

    private String nomeClubeCarreira;
    private String de;
    private String ate;
    private boolean atual;
    private String posicaoClubeCarreira;
    private int qntdPartidas;
    private int qntdGols;
    private int qntdAssistencia;
    private int qntdGolsSofridos;

    /**
     * Este construtor é para os jogadores das posicoes: atacante, meio campo, lateral, volante e ala, com uma data de inicio e fim no clube.
     * @param nomeClubeCarreira
     * @param de
     * @param ate
     * @param posicaoClubeCarreira
     * @param qntdPartidas
     * @param qntdGols
     * @param qntdAssistencia
     */
    public Carreira(String nomeClubeCarreira, String de, String ate, String posicaoClubeCarreira, int qntdPartidas, int qntdGols, int qntdAssistencia) {
        this.nomeClubeCarreira = nomeClubeCarreira;
        this.de = de;
        this.ate = ate;
        this.posicaoClubeCarreira = posicaoClubeCarreira;
        this.qntdPartidas = qntdPartidas;
        this.qntdGols = qntdGols;
        this.qntdAssistencia = qntdAssistencia;
    }

    /**
     * Este contrutor é para os jogadores das posicoes: atacante, meio campo, lateral, volante e ala, que tem uma data de inicio no clube e ainda esta atuando por ele.
     * @param nomeClubeCarreira
     * @param de
     * @param atual
     * @param posicaoClubeCarreira
     * @param qntdPartidas
     * @param qntdGols
     * @param qntdAssistencia
     */
    public Carreira(String nomeClubeCarreira, String de, boolean atual, String posicaoClubeCarreira, int qntdPartidas, int qntdGols, int qntdAssistencia) {
        this.nomeClubeCarreira = nomeClubeCarreira;
        this.de = de;
        this.atual = atual;
        this.posicaoClubeCarreira = posicaoClubeCarreira;
        this.qntdPartidas = qntdPartidas;
        this.qntdGols = qntdGols;
        this.qntdAssistencia = qntdAssistencia;
    }

    /**
     * Este construtor é para os jogadores das posicoes: goleiro e zagueiro, que tem uma data de inicio no clube e ainda esta atuando por ele.
     * @param nomeClubeCarreira
     * @param de
     * @param ate
     * @param posicaoClubeCarreira
     * @param qntdPartidas
     * @param qntdGols
     * @param qntdAssistencia
     * @param qntdGolsSofridos
     */
    public Carreira(String nomeClubeCarreira, String de, String ate, String posicaoClubeCarreira, int qntdPartidas, int qntdGols, int qntdAssistencia, int qntdGolsSofridos) {
        this.nomeClubeCarreira = nomeClubeCarreira;
        this.de = de;
        this.ate = ate;
        this.posicaoClubeCarreira = posicaoClubeCarreira;
        this.qntdPartidas = qntdPartidas;
        this.qntdGols = qntdGols;
        this.qntdAssistencia = qntdAssistencia;
        this.qntdGolsSofridos = qntdGolsSofridos;
    }

    /**
     * Este contrutor é para os jogadores das posicoes: goleiro e zagueiro, que tem uma data de inicio no clube e ainda esta atuando por ele.
     * @param nomeClubeCarreira
     * @param de
     * @param atual
     * @param posicaoClubeCarreira
     * @param qntdPartidas
     * @param qntdGols
     * @param qntdAssistencia
     * @param qntdGolsSofridos
     */
    public Carreira(String nomeClubeCarreira, String de, boolean atual, String posicaoClubeCarreira, int qntdPartidas, int qntdGols, int qntdAssistencia, int qntdGolsSofridos) {
        this.nomeClubeCarreira = nomeClubeCarreira;
        this.de = de;
        this.atual = atual;
        this.posicaoClubeCarreira = posicaoClubeCarreira;
        this.qntdPartidas = qntdPartidas;
        this.qntdGols = qntdGols;
        this.qntdAssistencia = qntdAssistencia;
        this.qntdGolsSofridos = qntdGolsSofridos;
    }

    public String getNomeClubeCarreira() {
        return nomeClubeCarreira;
    }

    public void setNomeClubeCarreira(String nomeClubeCarreira) {
        this.nomeClubeCarreira = nomeClubeCarreira;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getAte() {
        return ate;
    }

    public void setAte(String ate) {
        this.ate = ate;
    }

    public boolean isAtual() {
        return atual;
    }

    public void setAtual(boolean atual) {
        this.atual = atual;
    }

    public String getPosicaoClubeCarreira() {
        return posicaoClubeCarreira;
    }

    public void setPosicaoClubeCarreira(String posicaoClubeCarreira) {
        this.posicaoClubeCarreira = posicaoClubeCarreira;
    }

    public int getQntdPartidas() {
        return qntdPartidas;
    }

    public void setQntdPartidas(int qntdPartidas) {
        this.qntdPartidas = qntdPartidas;
    }

    public int getQntdGols() {
        return qntdGols;
    }

    public void setQntdGols(int qntdGols) {
        this.qntdGols = qntdGols;
    }

    public int getQntdAssistencia() {
        return qntdAssistencia;
    }

    public void setQntdAssistencia(int qntdAssistencia) {
        this.qntdAssistencia = qntdAssistencia;
    }

    public int getQntdGolsSofridos() {
        return qntdGolsSofridos;
    }

    public void setQntdGolsSofridos(int qntdGolsSofridos) {
        this.qntdGolsSofridos = qntdGolsSofridos;
    }
}
