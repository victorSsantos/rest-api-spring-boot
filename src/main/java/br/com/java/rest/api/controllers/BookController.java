package br.com.java.rest.api.controllers;

import br.com.java.rest.api.services.BookService;
import br.com.java.rest.api.services.dto.BookDTO;
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
@RequestMapping(value = "/api/books/v1")
@Tag(name = "Book", description = "Endpoints for management Books")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping
    @Operation(summary = "Finds all Books", description = "Finds all Books",
        tags = {"Books"},
        responses = {
                @ApiResponse(description = "success", responseCode = "200",
                content = {
                        @Content(
                                mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = BookDTO.class))
                        )
                }),
                @ApiResponse(description = "bad request", responseCode = "400", content = @Content),
                @ApiResponse(description = "unauthorized", responseCode = "401", content = @Content),
                @ApiResponse(description = "not found", responseCode = "404", content = @Content),
                @ApiResponse(description = "internal error", responseCode = "400", content = @Content)
    })
    public ResponseEntity<List<BookDTO>> findAll(){
        List<BookDTO> result = service.findAll();
        result.forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getId())).withSelfRel()));
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Finds a Book by Id", description = "Finds a Book by Id",
        tags = {"Books"},
        responses = {
                @ApiResponse(description = "success", responseCode = "200", content = @Content(schema = @Schema(implementation = BookDTO.class))),
                @ApiResponse(description = "no content", responseCode = "204", content = @Content),
                @ApiResponse(description = "bad request", responseCode = "400", content = @Content),
                @ApiResponse(description = "unauthorized", responseCode = "401", content = @Content),
                @ApiResponse(description = "not found", responseCode = "404", content = @Content),
                @ApiResponse(description = "internal error", responseCode = "400", content = @Content)
        })
    public ResponseEntity<BookDTO> findById(@PathVariable Long id){
        BookDTO result = service.findById(id);
        result.add(linkTo(methodOn(BookController.class).findById(result.getId())).withSelfRel());
        return ResponseEntity.ok(result);
    }

    @PostMapping
    @Operation(summary = "Adds a new Book", description = "Adds a new Book",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "created", responseCode = "201", content = @Content(schema = @Schema(implementation = BookDTO.class))),
                    @ApiResponse(description = "bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "internal error", responseCode = "400", content = @Content)
            })
    public ResponseEntity<BookDTO> insert(@RequestBody BookDTO book){
        var result = service.insert(book);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result.getId()).toUri();
        result.add(linkTo(methodOn(BookController.class).findById(result.getId())).withSelfRel());
        return ResponseEntity.created(uri).body(result);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Updates a Book", description = "Updates a Book",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "no content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "internal error", responseCode = "400", content = @Content)
            })
    public ResponseEntity<Void> update(@RequestBody BookDTO book, @PathVariable Long id){
        service.update(book, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletes a Book", description = "Deletes a Book",
            tags = {"Books"},
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
