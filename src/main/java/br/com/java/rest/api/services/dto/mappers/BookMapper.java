package br.com.java.rest.api.services.dto.mappers;

import br.com.java.rest.api.model.entities.Book;
import br.com.java.rest.api.services.dto.BookDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

      BookDTO mapToDTO(Book obj);
      Book mapToEntity(BookDTO dto);
}
