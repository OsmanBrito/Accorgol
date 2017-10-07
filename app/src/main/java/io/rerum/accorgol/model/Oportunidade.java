package io.rerum.accorgol.model;

/**
 * Created by osman on 26/09/2017.
 */

public class Oportunidade {

    private int idOportunidade;
    private String posicao;
    private String peDominante;
    private double alturaMin;
    private int anoNascimento;
    private String cidade;
    private String estado;

    public int getIdOportunidade() {
        return idOportunidade;
    }

    public void setIdOportunidade(int idOportunidade) {
        this.idOportunidade = idOportunidade;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public String getPeDominante() {
        return peDominante;
    }

    public void setPeDominante(String peDominante) {
        this.peDominante = peDominante;
    }

    public double getAlturaMin() {
        return alturaMin;
    }

    public void setAlturaMin(double alturaMin) {
        this.alturaMin = alturaMin;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
