package io.rerum.accorgol.model;

/**
 * Created by osman on 26/09/2017.
 */

public class Jogador {

    //: Nome completo, posição, pé dominante, altura, peso, e-mail, RG, ano de nascimento, endereço, bairro, cidade, estado, CEP, celular, senha, confirmar senha.

    private int idJogador;
    private String nomeCompleto;
    private String posicao;
    private String peDominante;
    private String altura;
    private String peso;
    private String email;
    private String RG;
    private String senha;
    private String anoNascimento;;
    private String bairro;
    private String cidade;
    private String estado;
    private String celular;
    private int fotoJogador;

    public Jogador(String nomeCompleto, String posicao, String peDominante, String altura, String peso, String email, String RG, String senha, String anoNascimento, String bairro, String cidade, String estado, String celular) {
        this.nomeCompleto = nomeCompleto;
        this.posicao = posicao;
        this.peDominante = peDominante;
        this.altura = altura;
        this.peso = peso;
        this.email = email;
        this.RG = RG;
        this.senha = senha;
        this.anoNascimento = anoNascimento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.celular = celular;
    }

    public Jogador(String email, String senha, String nome, String RG,String posicao, String peDominante, String anoNascimento) {
        this.email = email;
        this.senha = senha;
        this.nomeCompleto = nome;
        this.RG = RG;
        this.posicao = posicao;
        this.peDominante = peDominante;
        this.anoNascimento = anoNascimento;
    }

    public int getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(int idJogador) {
        this.idJogador = idJogador;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
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

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(String anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
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

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public int getFotoJogador() {
        return fotoJogador;
    }

    public void setFotoJogador(int fotoJogador) {
        this.fotoJogador = fotoJogador;
    }
}
