package com.desafio.desafiojava.dto;

import com.desafio.desafiojava.entities.Endereco;

public class EnderecoDTO {

    private Long id;
    private String logradouro;
    private String cep;
    private Integer numero;
    private String cidade;
    private Boolean enderecoAtual;
    private Long pessoaId;

    public EnderecoDTO() {
    }

    public EnderecoDTO(Long id, String logradouro, String cep, Integer numero, String cidade, Boolean enderecoAtual, Long pessoaId) {
        this.id = id;
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
        this.enderecoAtual = enderecoAtual;
        this.pessoaId = pessoaId;
    }

    public EnderecoDTO(Endereco entity) {
        id = entity.getId();
        logradouro = entity.getLogradouro();
        cep = entity.getCep();
        numero = entity.getNumero();
        cidade = entity.getCidade();
        enderecoAtual = entity.getEnderecoAtual();
        pessoaId = entity.getPessoa().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Boolean getEnderecoAtual() {
        return enderecoAtual;
    }

    public void setEnderecoAtual(Boolean enderecoAtual) {
        this.enderecoAtual = enderecoAtual;
    }

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }
}
