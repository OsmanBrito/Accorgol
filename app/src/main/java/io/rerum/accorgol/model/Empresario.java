package io.rerum.accorgol.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by osman on 26/09/2017.
 */

public class Empresario implements Serializable{

    private int idEmpresario;
    private String nomeCompleto;
    private String email;
    private String cpf;
    private Date dataDeNascimento;

    private String data;

    private String endereco;
    private String bairro;
    private String cidade;
    private String estado;
    private Long cep;
    private Long celular;
    private String senha;
    private String empresa;
    private boolean agenteFifa;
    private String numeroRegistro;
    private Oportunidade oportunidade[];

    //Campos que serão salvos com as duas etapas de cadastro efetuadas com sucesso.
    public Empresario(String nomeCompleto, String email, String cpf, String dataNascimento, String endereco, String bairro, String cidade, String estado, Long cep, Long celular, String senha) {

        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.cpf = cpf;
        this.data = dataNascimento;
        this.endereco = endereco;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.celular = celular;
        this.senha = senha;

    }

    public Empresario(String nomeCompleto, String email, String cpf, String dataDeNascimento, String endereco, String bairro, String cidade, String estado, Long cep, Long celular, String senha, String empresa, String numeroRegistro) {
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.cpf = cpf;
        this.data = dataDeNascimento;
        this.endereco = endereco;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.celular = celular;
        this.senha = senha;
        this.empresa = empresa;
        this.agenteFifa = true;
        this.numeroRegistro = numeroRegistro;
    }

    public Empresario(){

    }

    //cadastrar novo empresario no banco
    public Empresario(String nome, String email, String cpf, String dataNascimento, String endereco, String bairro, String cidade, String estado, Long cep, Long celular, String empresa, String registro) {

        this.nomeCompleto = nome;
        this.email = email;
        this.cpf = cpf;
        this.data = dataNascimento;
        this.endereco = endereco;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.celular = celular;
        this.empresa = empresa;
        this.numeroRegistro = registro;
        this.agenteFifa = true;

    }

    public int getIdEmpresario() {
        return idEmpresario;
    }

    public void setIdEmpresario(int idEmpresario) {
        this.idEmpresario = idEmpresario;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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

    public Long getCep() {
        return cep;
    }

    public void setCep(Long cep) {
        this.cep = cep;
    }

    public Long getCelular() {
        return celular;
    }

    public void setCelular(Long celular) {
        this.celular = celular;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public boolean isAgenteFifa() {
        return agenteFifa;
    }

    public void setAgenteFifa(boolean agenteFifa) {
        this.agenteFifa = agenteFifa;
    }

    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(String numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public Oportunidade[] getOportunidade() {
        return oportunidade;
    }

    public void setOportunidade(Oportunidade[] oportunidade) {
        this.oportunidade = oportunidade;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o){
        return this.idEmpresario == ((Empresario)o).idEmpresario;
    }

    @Override
    public int hashCode(){
        return this.idEmpresario;
    }



}
