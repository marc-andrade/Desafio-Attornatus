package com.desafio.desafiojava.dto;

import com.desafio.desafiojava.entities.Endereco;
import com.desafio.desafiojava.entities.Pessoa;

import java.time.LocalDate;
import java.util.List;

public class PessoaDTO {

    private Long id;
    private String nome;
    private LocalDate dataDeNascimento;

    public PessoaDTO() {
    }

    public PessoaDTO(Long id, String nome, LocalDate dataDeNascimento, List<Endereco> enderecos) {
        this.id = id;
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
    }

    public PessoaDTO(Pessoa entity) {
        id = entity.getId();
        nome = entity.getNome();
        dataDeNascimento = entity.getDataDeNascimento();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

}
