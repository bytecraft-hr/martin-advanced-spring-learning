package com.example.demo.dto;
import com.example.demo.entity.Person; 

public class PersonDTO {
    private String firstName;
    private String lastName;
    private String email;

    public PersonDTO(Person person) {
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.email = person.getEmail();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}

