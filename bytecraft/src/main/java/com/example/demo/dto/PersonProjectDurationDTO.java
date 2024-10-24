package com.example.demo.dto;

public class PersonProjectDurationDTO {
    private String firstName;
    private String lastName;
    private int totalDaysOnProjects;

    public PersonProjectDurationDTO(String firstName, String lastName, int totalDaysOnProjects) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalDaysOnProjects = totalDaysOnProjects;
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

public int getTotalDaysOnProjects() {
    return totalDaysOnProjects;
}

public void setTotalDaysOnProjects(int totalDaysOnProjects) {
    this.totalDaysOnProjects = totalDaysOnProjects;
}


}