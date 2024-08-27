package br.com.java.rest.api.dto.mappers;

import br.com.java.rest.api.model.entities.Person;
import br.com.java.rest.api.dto.PersonDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {

      PersonDTO mapToDTO(Person obj);
      Person mapToEntity(PersonDTO dto);
}
