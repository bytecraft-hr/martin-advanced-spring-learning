package com.example.demo.dto;
import lombok.Data;
import java.util.List;

@Data
public class PersonProjectDTO {
    private String firstName;
    private String lastName;
    private String email;
    private List<ProjectDTO> projects; 
    private List<SkillDTO> skills; 


    public PersonProjectDTO(String firstName, String lastName, String email, List<ProjectDTO> projects, List<SkillDTO> skills) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.projects = projects;
        this.skills = skills;
    }
}
