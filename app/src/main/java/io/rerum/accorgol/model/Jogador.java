package io.rerum.accorgol.model;

import com.beardedhen.androidbootstrap.BootstrapEditText;

import java.util.ArrayList;

/**
 * Created by osman on 26/09/2017.
 */

public class Jogador {

    //: Nome completo, posição, pé dominante, altura, peso, e-mail, RG, ano de nascimento, endereço, bairro, cidade, estado, CEP, celular, senha, confirmar senha.

    private String idJogador;
    private String nomeCompleto;
    private String posicao;
    private String peDominante;
    private String altura;
    private String RG;
    private String anoNascimento;
    private String cidade;
    private String estado;
    private String celular;
    private String urifoto;
    private String urivideo;
    private ArrayList<Conquista> conquista;
    private ArrayList<Conquista> carreira;

    public Jogador(String nomeCompleto, String posicao, String peDominante, String altura, String RG, String anoNascimento, String cidade, String estado, String celular) {
        this.nomeCompleto = nomeCompleto;
        this.posicao = posicao;
        this.peDominante = peDominante;
        this.altura = altura;
        this.RG = RG;
        this.anoNascimento = anoNascimento;
        this.cidade = cidade;
        this.estado = estado;
        this.celular = celular;
    }

    public Jogador(String nome, String RG,String posicao, String peDominante, String anoNascimento) {
        this.nomeCompleto = nome;
        this.RG = RG;
        this.posicao = posicao;
        this.peDominante = peDominante;
        this.anoNascimento = anoNascimento;
    }


    public Jogador(String posicao, String pe, String ano) {
        this.posicao = posicao;
        this.peDominante = pe;
        this.anoNascimento = ano;
    }

    public ArrayList<Conquista> getConquista() {
        return conquista;
    }

    public void setConquista(ArrayList<Conquista> conquista) {
        this.conquista = conquista;
    }

    public ArrayList<Conquista> getCarreira() {
        return carreira;
    }

    public void setCarreira(ArrayList<Conquista> carreira) {
        this.carreira = carreira;
    }

    public String getVideojogador() {
        return urivideo;
    }

    public void setVideojogador(String videojogador) {
        this.urivideo = videojogador;
    }

    public String getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(String idJogador) {
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

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
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

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getFotoJogador() {
        return urifoto;
    }

    public void setFotoJogador(String fotoJogador) {
        this.urifoto = fotoJogador;
    }
}
