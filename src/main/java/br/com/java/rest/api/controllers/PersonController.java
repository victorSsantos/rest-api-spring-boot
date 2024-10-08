package br.com.java.rest.api.controllers;

import br.com.java.rest.api.services.dto.PersonDTO;
import br.com.java.rest.api.services.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/persons/v1")
@Tag(name = "People", description = "Endpoints for management People")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping
    @Operation(summary = "Finds all People", description = "Finds all People",
        tags = {"People"},
        responses = {
                @ApiResponse(description = "success", responseCode = "200",
                content = {
                        @Content(
                                mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class))
                        )
                }),
                @ApiResponse(description = "bad request", responseCode = "400", content = @Content),
                @ApiResponse(description = "unauthorized", responseCode = "401", content = @Content),
                @ApiResponse(description = "not found", responseCode = "404", content = @Content),
                @ApiResponse(description = "internal error", responseCode = "400", content = @Content)
    })
    public ResponseEntity<List<PersonDTO>> findAll(){
        List<PersonDTO> result = service.findAll();
        result.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getId())).withSelfRel()));
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Finds an Person by Id", description = "Finds People by Id",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "success", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonDTO.class))),
                    @ApiResponse(description = "no content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "internal error", responseCode = "400", content = @Content)
            })
    public ResponseEntity<PersonDTO> findById(@PathVariable Long id){
        PersonDTO result = service.findById(id);
        result.add(linkTo(methodOn(PersonController.class).findById(result.getId())).withSelfRel());
        return ResponseEntity.ok(result);
    }

    @PostMapping
    @Operation(summary = "Adds a new Person", description = "Adds a new People",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "created", responseCode = "201", content = @Content(schema = @Schema(implementation = PersonDTO.class))),
                    @ApiResponse(description = "bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "internal error", responseCode = "400", content = @Content)
            })
    public ResponseEntity<PersonDTO> insert(@RequestBody PersonDTO person){
        var result = service.insert(person);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result.getId()).toUri();
        result.add(linkTo(methodOn(PersonController.class).findById(result.getId())).withSelfRel());
        return ResponseEntity.created(uri).body(result);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Updates a Person", description = "Updates a Person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "no content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "internal error", responseCode = "400", content = @Content)
            })
    public ResponseEntity<Void> update(@RequestBody PersonDTO person, @PathVariable Long id){
        service.update(person, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletes a Person", description = "Deletes a Person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "no content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "internal error", responseCode = "400", content = @Content)
            })
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
