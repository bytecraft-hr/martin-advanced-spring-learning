package com.example.demo.service;

import com.example.demo.entity.Project;
import com.example.demo.entity.ProjectCategory;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.ProjectCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private ProjectCategoryRepository projectCategoryRepository;

    // Dohvati sve projekte
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    // Spremi novi projekat
    public Project saveProject(Project project) {
        if (project.getProjectCategory() == null) {
            ProjectCategory defaultCategory = projectCategoryRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("Default category not found"));
            project.setProjectCategory(defaultCategory);
        }
        return projectRepository.save(project);
    }

    public void deleteProject(int projectId) {
        projectRepository.deleteById(projectId);
    }

    public Project getProjectById(int projectId) {
        Optional<Project> project = projectRepository.findById(projectId);
        return project.orElse(null);
    }

    public Project updateProject(int id, Project updatedProject) {
        Optional<Project> existingProject = projectRepository.findById(id);
        
        if (existingProject.isPresent()) {
            Project project = existingProject.get();
            project.setProjectName(updatedProject.getProjectName());
            project.setDescription(updatedProject.getDescription());
            project.setProjectCategory(updatedProject.getProjectCategory());
            return projectRepository.save(project);
        }
        
        return null;
    }


    public List<Object[]> getPersonRanks() {
        return projectRepository.getPersonProjectRanks();
    }
}
