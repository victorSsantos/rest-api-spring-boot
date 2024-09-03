package br.com.java.rest.api.services;

import br.com.java.rest.api.model.entities.Book;
import br.com.java.rest.api.repositories.BookRepository;
import br.com.java.rest.api.services.dto.BookDTO;
import br.com.java.rest.api.services.dto.mappers.BookMapper;
import br.com.java.rest.api.exceptions.DataIntegrityException;
import br.com.java.rest.api.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repo;
    @Autowired
    private BookMapper bookMapper;

    public List<BookDTO> findAll(){
        List<BookDTO> books = new ArrayList<>();
        repo.findAll().forEach(b -> books.add(bookMapper.mapToDTO(b)));
        return books;
    }

    public BookDTO findById(Long id){
        Book result = repo.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        return bookMapper.mapToDTO(result);
    }

    public BookDTO insert(BookDTO newData) {
        try{
            Book bookToInsert = bookMapper.mapToEntity(newData);
            return bookMapper.mapToDTO(repo.save(bookToInsert));
        }
        catch(DataIntegrityViolationException e){
            throw new DataIntegrityException(e.getCause().getMessage());
        }
    }

    public void delete(Long id){
        repo.deleteById(findById(id).getId());
    }

    public void update(BookDTO newData, Long id) {
        BookDTO source = findById(id);
        newData.setId(source.getId());
        repo.save(bookMapper.mapToEntity(newData));
    }
}
