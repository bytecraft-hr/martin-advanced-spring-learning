package com.example.demo.dto;

public class ProjectParticipantDTO {

    private String firstName;
    private String lastName;
    private String role;

    // Konstruktor, getteri i setteri
    public ProjectParticipantDTO(String firstName, String lastName, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
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

public String getRole() {
    return role;
}

public void setRole(String role) {
    this.role = role;
}
}
