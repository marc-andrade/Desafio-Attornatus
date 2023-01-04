package com.desafio.desafiojava.tests;

import com.desafio.desafiojava.dto.EnderecoDTO;
import com.desafio.desafiojava.dto.PessoaDTO;
import com.desafio.desafiojava.entities.Endereco;
import com.desafio.desafiojava.entities.Pessoa;

import java.time.LocalDate;

public class Factory {

    public static Pessoa createPessoa(){
        return new Pessoa(1L,"Marcos", LocalDate.parse("1997-06-21"));
    }

    public static PessoaDTO createPessoaDTO(){
        return new PessoaDTO(createPessoa());
    }

    public static Endereco createEndereco(){
        return new Endereco(1L,"Rua Fernando Bahia","67103-160",67,"Marituba",true,createPessoa());
    }

    public static EnderecoDTO createEnderecoDTO(){
        return new EnderecoDTO(1L,"Rua Fernando Bahia","67103-160",67,"Marituba",true,createPessoa().getId());
    }
}
