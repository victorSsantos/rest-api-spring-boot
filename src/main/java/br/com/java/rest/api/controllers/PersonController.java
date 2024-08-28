package br.com.java.rest.api.controllers;

import br.com.java.rest.api.services.dto.PersonDTO;
import br.com.java.rest.api.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/persons")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping
    public ResponseEntity<List<PersonDTO>> findAll(){
        List<PersonDTO> result = service.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonDTO> findById(@PathVariable Long id){
        PersonDTO result = service.findById(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<PersonDTO> insert(@RequestBody PersonDTO person){
        var result = service.insert(person);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(uri).body(result);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody PersonDTO person, @PathVariable Long id){
        service.update(person, id);
        return ResponseEntity.noContent().build();
    }
}
