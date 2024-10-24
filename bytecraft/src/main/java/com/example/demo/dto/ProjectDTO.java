package com.example.demo.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ProjectDTO {
    private String projectName;
    private String role;
    private LocalDate startDate;
    private LocalDate endDate;

    public ProjectDTO(String projectName, String role, LocalDate startDate, LocalDate endDate) {
        this.projectName = projectName;
        this.role = role;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
