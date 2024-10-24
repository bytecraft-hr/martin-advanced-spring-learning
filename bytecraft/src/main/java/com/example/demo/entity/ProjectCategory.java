package com.example.demo.entity;
import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class ProjectCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectCategoryId;
    public Long getProjectCategoryId() {
        return projectCategoryId;
    }

    public void setProjectCategoryId(Long projectCategoryId) {
        this.projectCategoryId = projectCategoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
    private String categoryName;

    @OneToMany(mappedBy = "projectCategory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
@JsonBackReference
private List<Project> projects;

}
