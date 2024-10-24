package com.example.demo.entity;

import jakarta.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectId;

    @Column(name = "project_name", nullable = false)
    @NotNull(message = "Project name cannot be null")
    @Size(min = 1, max = 100, message = "Project name must be between 1 and 100 characters")
    private String projectName;

    @Column(name = "description")
    @Size(max = 255, message = "Description must be at most 255 characters")
    private String description;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "project_category_id", nullable = false)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
private ProjectCategory projectCategory;


    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference // Sprječava cikličke reference prilikom serijalizacije
    private List<PersonProjects> personProjects;

    public Project() {
    }

    public Project(String projectName, String description, ProjectCategory projectCategory) {
        this.projectName = projectName;
        this.description = description;
        this.projectCategory = projectCategory;
    }

    // Getters and Setters
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProjectCategory getProjectCategory() {
        return projectCategory;
    }

    public void setProjectCategory(ProjectCategory projectCategory) {
        this.projectCategory = projectCategory;
    }

    public List<PersonProjects> getPersonProjects() {
        return personProjects;
    }

    public void setPersonProjects(List<PersonProjects> personProjects) {
        this.personProjects = personProjects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;
        Project project = (Project) o;
        return projectId == project.projectId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId);
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", description='" + description + '\'' +
                ", projectCategory=" + (projectCategory != null ? projectCategory.getCategoryName() : "null") +
                '}';
    }
}
