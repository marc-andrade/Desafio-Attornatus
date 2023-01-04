package com.desafio.desafiojava.resources;

import com.desafio.desafiojava.dto.EnderecoDTO;
import com.desafio.desafiojava.dto.PessoaDTO;
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
@RequestMapping("/pessoas")
public class PessoaResource {

    @Autowired
    private PessoaService service;
    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> findById(@PathVariable Long id) {
        PessoaDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<PessoaDTO>> findAll(Pageable pageable) {
        Page<PessoaDTO> list = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<PessoaDTO> create(@RequestBody PessoaDTO pessoaDTO){
        PessoaDTO newDto = service.create(pessoaDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PessoaDTO> update(@PathVariable Long id, @RequestBody PessoaDTO dto) {
        PessoaDTO newDto = service.update(id, dto);
        return ResponseEntity.ok().body(newDto);
    }

    @GetMapping("/enderecos/{id}")
    public ResponseEntity<List<EnderecoDTO>> findByPessoaId(@PathVariable Long id) {
        List<EnderecoDTO> list = enderecoService.findByPessoaId(id);
        return ResponseEntity.ok().body(list);
    }
}
