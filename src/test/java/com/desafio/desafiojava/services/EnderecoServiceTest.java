package com.desafio.desafiojava.services;

import com.desafio.desafiojava.dto.EnderecoDTO;
import com.desafio.desafiojava.dto.PessoaDTO;
import com.desafio.desafiojava.entities.Endereco;
import com.desafio.desafiojava.entities.Pessoa;
import com.desafio.desafiojava.repositories.EnderecoRepository;
import com.desafio.desafiojava.repositories.PessoaRepository;
import com.desafio.desafiojava.tests.Factory;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class EnderecoServiceTest {

    @InjectMocks
    private EnderecoService service;
    @Mock
    private EnderecoRepository repository;
    @Mock
    private PessoaService pessoaService;
    @Mock
    private PessoaRepository pessoaRepository;

    private long existingId;
    private Endereco endereco;
    private EnderecoDTO enderecoDTO;
    private Pessoa pessoa;
    private List<Endereco> list;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        endereco = Factory.createEndereco();
        enderecoDTO = Factory.createEnderecoDTO();
        pessoa = Factory.createPessoa();
        list = List.of(endereco);

        when(repository.findEnderecosByPessoaId(existingId)).thenReturn(list);

        when(repository.save(any())).thenReturn(endereco);
        when(pessoaRepository.getReferenceById(existingId)).thenReturn(pessoa);
        when(repository.findAll()).thenReturn(list);
        when(repository.saveAll(any())).thenReturn(list);
    }

    @Test
    void whenCreateThenReturnSuccess() {

        EnderecoDTO response = service.create(enderecoDTO);


        assertNotNull(response);
        assertEquals(EnderecoDTO.class, response.getClass());

        assertEquals(1L, response.getId());
        assertEquals("Rua Fernando Bahia", response.getLogradouro());
        assertEquals("67103-160", response.getCep());
        assertEquals(67, response.getNumero());
        assertEquals("Marituba", response.getCidade());
        assertEquals(1L, response.getPessoaId());

    }

    @Test
    void findByPessoaIdShouldSearchListOfEnderecoWhenPessoaIdExists() {

        List<EnderecoDTO> response = service.findByPessoaId(existingId);

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(EnderecoDTO.class, response.get(0).getClass());

        assertEquals(1L, response.get(0).getId());
        assertEquals("Rua Fernando Bahia", response.get(0).getLogradouro());
        assertEquals("67103-160", response.get(0).getCep());
        assertEquals(67, response.get(0).getNumero());
        assertEquals("Marituba", response.get(0).getCidade());
        assertEquals(true, response.get(0).getEnderecoAtual());
        assertEquals(1L, response.get(0).getPessoaId());
    }
}