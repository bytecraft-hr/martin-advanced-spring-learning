package com.example.demo.controller;
import com.example.demo.entity.Project;
import com.example.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
@Validated
public class ProjectController {

    @Autowired
    private ProjectService projectService;


    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable("id") int id) {
        Project project = projectService.getProjectById(id);
        if (project == null) {
            throw new RuntimeException("Project not found with id: " + id);
        }
        return project;
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody @Valid Project project) {
        Project savedProject = projectService.saveProject(project);
        return new ResponseEntity<>(savedProject, HttpStatus.CREATED); 
    }

    @PutMapping("/{id}")
    
    public ResponseEntity<String> updateProject(@PathVariable("id") int id, @RequestBody @Valid Project updatedProject) {
        Project existingProject = projectService.getProjectById(id);
        if (existingProject == null) {
            throw new RuntimeException("Project not found with id: " + id);
        }
    
        existingProject.setProjectName(updatedProject.getProjectName());
        existingProject.setDescription(updatedProject.getDescription());
        projectService.saveProject(existingProject);
    
        String message = "Projekat s ID-em " + id + " uspješno je ažuriran.";
        return ResponseEntity.ok(message); // Vraća status 200 OK sa porukom
    }
    


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable("id") int id) {
        projectService.deleteProject(id);
        String message = "Obrisali ste projekat s ID-em " + id;
        return ResponseEntity.ok(message); // Vraća status 200 OK sa porukom
    }
    



    @GetMapping("/ranks")
    public List<Object[]> getPersonProjectRanks() {
        return projectService.getPersonRanks();
    }
  
}
