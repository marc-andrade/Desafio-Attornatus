package com.desafio.desafiojava.services;

import com.desafio.desafiojava.dto.PessoaDTO;
import com.desafio.desafiojava.entities.Pessoa;
import com.desafio.desafiojava.repositories.PessoaRepository;
import com.desafio.desafiojava.services.exceptions.ResourceNotFoundException;
import com.desafio.desafiojava.tests.Factory;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PessoaServiceTest {

    @InjectMocks
    private PessoaService service;
    @Mock
    private PessoaRepository repository;

    private long existingId;
    private long nonExistingId;
    private Pessoa pessoa;
    private PessoaDTO pessoaDTO;
    private PageImpl<Pessoa> page;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        nonExistingId = 2L;
        pessoa = Factory.createPessoa();
        pessoaDTO = Factory.createPessoaDTO();
        page = new PageImpl<>(List.of(pessoa));

        when(repository.findAll((Pageable)any())).thenReturn(page);

        when(repository.findById(existingId)).thenReturn(Optional.of(pessoa));
        when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

        when(repository.save(any())).thenReturn(pessoa);

        when(repository.getReferenceById(existingId)).thenReturn(pessoa);
        when(repository.getReferenceById(nonExistingId)).thenThrow(EntityNotFoundException.class);
    }

    @Test
    void whenCreateThenReturnSuccess() {

        PessoaDTO response = service.create(pessoaDTO);

        assertNotNull(response);
        assertEquals(PessoaDTO.class, response.getClass());

        assertEquals(pessoaDTO.getId(), response.getId());
        assertEquals(pessoaDTO.getNome(), response.getNome());
        assertEquals(pessoaDTO.getDataDeNascimento(), response.getDataDeNascimento());
    }

    @Test
    void findByIdShouldSearchPessoaDTOWhenIdIsExisting() {

        PessoaDTO response = service.findById(existingId);

        assertNotNull(response);
        assertEquals(PessoaDTO.class, response.getClass());
    }

    @Test
    void findByIdShouldThrowResourceNotFoundWhenIdDoesNotExisting() {

        assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(nonExistingId);
        });
    }

    @Test
    void findAllPagedShouldReturnPage() {

        Pageable pageable = PageRequest.of(0, 12);

        Page<PessoaDTO> result = service.findAllPaged(pageable);

        Assertions.assertNotNull(result);
    }

    @Test
    void updateShouldReturnProductDTOWhenIdExists() {

        PessoaDTO response = service.update(existingId, pessoaDTO);

        assertNotNull(response);
    }

    @Test
    void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {

        assertThrows(ResourceNotFoundException.class, () -> {
            service.update(nonExistingId, pessoaDTO);
        });

    }
}