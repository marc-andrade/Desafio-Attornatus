package com.desafio.desafiojava.resources;

import com.desafio.desafiojava.dto.EnderecoDTO;
import com.desafio.desafiojava.dto.PessoaDTO;
import com.desafio.desafiojava.entities.Endereco;
import com.desafio.desafiojava.services.EnderecoService;
import com.desafio.desafiojava.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoResource {

    @Autowired
    private EnderecoService service;

    @PostMapping
    public ResponseEntity<EnderecoDTO> create(@RequestBody EnderecoDTO enderecoDTO){
        EnderecoDTO newDto = service.create(enderecoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

}
