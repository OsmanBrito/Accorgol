package io.rerum.accorgol.model;

/**
 * Created by osman on 22/11/2017.
 */

public class Conquista {

    private String idConquista;
    private String nomeConquista;
    private String nomeClube;
    private String ano;
    private String urifoto;


    public Conquista(String nomeConquista, String ano, String urifoto, String nomeClube) {
        this.nomeConquista = nomeConquista;
        this.nomeClube = nomeClube;
        this.ano = ano;
        this.urifoto = urifoto;
    }

    public Conquista() {
    }

    public String getIdConquista() {
        return idConquista;
    }

    public void setIdConquista(String idConquista) {
        this.idConquista = idConquista;
    }

    public String getURIFoto() {
        return urifoto;
    }

    public void setURIFoto(String URIFoto) {
        this.urifoto = URIFoto;
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
