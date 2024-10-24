package com.example.demo.dto;

import lombok.Data;
import java.util.List;


@Data
public class PersonSkillDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String skillCategory;
    private String skillName;
    private List<String> skillNames;

    public PersonSkillDTO(String firstName, String lastName, String email, String skillCategory, String skillName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.skillCategory = skillCategory;
        this.skillName = skillName;
    }



    // Getteri
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }



    public List<String> getSkillName() {
        return skillNames;
    }

    // Setteri
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setSkillName(List<String> skillNames) {
        this.skillNames = skillNames;
    }
}
