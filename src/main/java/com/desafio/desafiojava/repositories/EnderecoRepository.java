package com.desafio.desafiojava.repositories;

import com.desafio.desafiojava.entities.Endereco;
import com.desafio.desafiojava.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query("select e from Endereco e where e.pessoa.id = :id")
    List<Endereco> findEnderecosByPessoaId(@Param("id") Long id);
}
