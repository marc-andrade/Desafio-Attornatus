package com.desafio.desafiojava.services;

import com.desafio.desafiojava.dto.EnderecoDTO;
import com.desafio.desafiojava.dto.PessoaDTO;
import com.desafio.desafiojava.entities.Endereco;
import com.desafio.desafiojava.entities.Pessoa;
import com.desafio.desafiojava.repositories.EnderecoRepository;
import com.desafio.desafiojava.repositories.PessoaRepository;
import com.desafio.desafiojava.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;
    @Autowired
    private PessoaService pessoaService;
    @Autowired
    private PessoaRepository pessoaRepository;

    @Transactional
    public EnderecoDTO create(EnderecoDTO dto) {
        Endereco entity = new Endereco();
        pessoaService.findById(dto.getPessoaId());
        copyDtoToEntity(dto, entity);
        entity.setPessoa(pessoaRepository.getReferenceById(dto.getPessoaId()));
        if (dto.getEnderecoAtual()){
            List<Endereco> list = repository.findAll();
            list.forEach(endereco -> endereco.setEnderecoAtual(false));
            repository.saveAll(list);
        }
        entity = repository.save(entity);
        return new EnderecoDTO(entity);
    }

    public List<EnderecoDTO> findByPessoaId(Long id) {
        pessoaService.findById(id);
        List<Endereco> list = repository.findEnderecosByPessoaId(id);
        if(list.isEmpty()) throw new EntityNotFoundException("This person has no addresses");
        List<EnderecoDTO> listDto = new ArrayList<>();
        list.forEach(endereco -> listDto.add(new EnderecoDTO(endereco)));
        return listDto;
    }

    private void copyDtoToEntity(EnderecoDTO dto, Endereco entity) {
        entity.setLogradouro(dto.getLogradouro());
        entity.setCep(dto.getCep());
        entity.setNumero(dto.getNumero());
        entity.setCidade(dto.getCidade());
        entity.setEnderecoAtual(dto.getEnderecoAtual());
    }
}


