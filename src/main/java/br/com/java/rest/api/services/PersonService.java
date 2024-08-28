package br.com.java.rest.api.services;

import br.com.java.rest.api.services.dto.PersonDTO;
import br.com.java.rest.api.services.dto.mappers.PersonMapper;
import br.com.java.rest.api.model.entities.Person;
import br.com.java.rest.api.repositories.PersonRepository;
import br.com.java.rest.api.services.exceptions.DataIntegrityException;
import br.com.java.rest.api.services.exceptions.ResourceNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        Person result = repo.findById(id).orElseThrow(ResourceNotFoundException::new);
        return personMapper.mapToDTO(result);
    }

    public PersonDTO insert(PersonDTO newPerson) {
        try{
            Person personToInsert = personMapper.mapToEntity(newPerson);
            return personMapper.mapToDTO(repo.save(personToInsert));
        }
        catch(DataIntegrityViolationException e){
            throw new DataIntegrityException(e.getCause().getMessage());
        }
    }

    public void delete(Long id){
        repo.deleteById(findById(id).getId());
    }

    public void update(PersonDTO newData, Long id) {
        PersonDTO source = findById(id);
        newData.setId(source.getId());
        repo.save(personMapper.mapToEntity(newData));
    }
}
