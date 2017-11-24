package io.rerum.accorgol.model;

/**
 * Created by osman on 22/11/2017.
 */

public class Conquista {

    private String nomeConquista;
    private String nomeClube;
    private String ano;
    private String URIFoto;

    public Conquista(String nomeConquista, String nomeClube, String ano, String URIFoto) {
        this.nomeConquista = nomeConquista;
        this.nomeClube = nomeClube;
        this.ano = ano;
        this.URIFoto = URIFoto;
    }

    public String getURIFoto() {
        return URIFoto;
    }

    public void setURIFoto(String URIFoto) {
        this.URIFoto = URIFoto;
    }

    public String getNomeConquista() {
        return nomeConquista;
    }

    public void setNomeConquista(String nomeConquista) {
        this.nomeConquista = nomeConquista;
    }

    public String getNomeClube() {
        return nomeClube;
    }

    public void setNomeClube(String nomeClube) {
        this.nomeClube = nomeClube;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }
}
