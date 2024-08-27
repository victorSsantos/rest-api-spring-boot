package br.com.java.rest.api.dto;

import br.com.java.rest.api.model.enums.Gender;
import org.springframework.stereotype.Component;

@Component
public class PersonDTO {

    private Long id;
    private String firstName;
    private String lastName;

    private String address;
    private Gender gender;

    public PersonDTO() {
    }

    public PersonDTO(Long id, String firstName, String lastName, String address, Gender gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
