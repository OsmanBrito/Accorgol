package io.rerum.accorgol.model;

/**
 * Created by osman on 26/09/2017.
 */

public class Oportunidade {

    private String idOportunidade;
    private String posicao;
    private String peDominante;
    private String alturaMin;
    private String anoNascimento;
    private String cidade;
    private String estado;

    public Oportunidade() {}

    public Oportunidade(String posicao, String altura, String ano, String cidade, String pe, String estado) {
        this.posicao = posicao;
        this.alturaMin = altura;
        this.anoNascimento = ano;
        this.cidade = cidade;
        this.peDominante = pe;
        this.estado = estado;
    }

    public String getIdOportunidade() {
        return idOportunidade;
    }

    public void setIdOportunidade(String idOportunidade) {
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

    public String getAlturaMin() {
        return alturaMin;
    }

    public void setAlturaMin(String alturaMin) {
        this.alturaMin = alturaMin;
    }

    public String getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(String anoNascimento) {
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
