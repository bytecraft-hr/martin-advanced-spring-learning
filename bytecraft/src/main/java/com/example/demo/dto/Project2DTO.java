package com.example.demo.dto;
import java.util.List;
public class Project2DTO {
    private String projectName;
    private List<ProjectParticipantDTO> participants;

    // Konstruktor, getteri i setteri
    public Project2DTO(String projectName, List<ProjectParticipantDTO> participants) {
        this.projectName = projectName;
        this.participants = participants;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<ProjectParticipantDTO> getParticipants() {
        return participants;
    }

    public void setParticipants(List<ProjectParticipantDTO> participants) {
        this.participants = participants;
    }
}

