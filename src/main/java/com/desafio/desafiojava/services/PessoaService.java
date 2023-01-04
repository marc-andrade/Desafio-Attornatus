package com.desafio.desafiojava.services;

import com.desafio.desafiojava.dto.PessoaDTO;
import com.desafio.desafiojava.entities.Pessoa;
import com.desafio.desafiojava.repositories.PessoaRepository;
import com.desafio.desafiojava.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    @Transactional
    public PessoaDTO create(PessoaDTO dto) {
        Pessoa entity = new Pessoa();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new PessoaDTO(entity);
    }

    public PessoaDTO findById(Long id) {
        Optional<Pessoa> obj = repository.findById(id);
        Pessoa entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new PessoaDTO(entity);
    }

    public Page<PessoaDTO> findAllPaged(Pageable pageable) {
        Page<Pessoa> list = repository.findAll(pageable);
        return list.map(PessoaDTO::new);
    }

    @Transactional
    public PessoaDTO update(Long id, PessoaDTO dto) {
        try {
            findById(id);
            Pessoa entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new PessoaDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    private void copyDtoToEntity(PessoaDTO dto, Pessoa entity) {
        entity.setNome(dto.getNome());
        entity.setDataDeNascimento(dto.getDataDeNascimento());
    }
}


