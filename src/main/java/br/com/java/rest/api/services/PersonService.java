package br.com.java.rest.api.services;

import br.com.java.rest.api.dto.PersonDTO;
import br.com.java.rest.api.dto.mappers.PersonMapper;
import br.com.java.rest.api.model.entities.Person;
import br.com.java.rest.api.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repo;

    @Autowired
    private PersonMapper personMapper;

    public List<PersonDTO> findAll(){
        List<PersonDTO> persons = new ArrayList<>();
        repo.findAll().forEach(person -> persons.add(personMapper.mapToDTO(person)));
        return persons;
    }

    public PersonDTO findById(Long id){
        Person result = repo.findById(id).orElse(null);
        return personMapper.mapToDTO(result);
    }

    public PersonDTO insert(PersonDTO personDTO) {
        Person personToInsert = personMapper.mapToEntity(personDTO);
        return personMapper.mapToDTO(repo.save(personToInsert));
    }
}
