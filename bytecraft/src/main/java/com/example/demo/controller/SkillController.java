package com.example.demo.controller;

import com.example.demo.entity.Skill;
import com.example.demo.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/skills")
@Validated 
public class SkillController {

    @Autowired
    private SkillService skillService;




    @GetMapping
    public List<Skill> getAllSkills() {
        return skillService.getAllSkills();
    }

    @GetMapping("/{id}")
    public Skill getSkillById(@PathVariable("id") int id) {
        Skill skill = skillService.getSkillById(id);
        if (skill == null) {
            throw new RuntimeException("Skill not found with id: " + id);
        }
        return skill;
    }

    @PostMapping
    public ResponseEntity<Skill> createSkill(@RequestBody @Valid Skill skill) {
        Skill savedSkill = skillService.saveSkill(skill);
        return new ResponseEntity<>(savedSkill, HttpStatus.CREATED); // Vraća status 201 CREATED
    }

    @PutMapping("/{id}")
public ResponseEntity<String> updateSkill(@PathVariable("id") int id, @RequestBody @Valid Skill updatedSkill) {
    Skill existingSkill = skillService.getSkillById(id);
    if (existingSkill == null) {
        throw new RuntimeException("Skill nije pronaden id(: " + id+")");
    }
    
    existingSkill.setSkillName(updatedSkill.getSkillName());
    Skill savedSkill = skillService.saveSkill(existingSkill);

    String message = "Skill s ID " + id + " je uspjesno ažuriran " + savedSkill.getSkillName();
    return ResponseEntity.ok(message); 
}


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSkill(@PathVariable("id") int id) {
        skillService.deleteSkill(id);
        String message = "Obrisali ste skill s ID-em " + id;
        return ResponseEntity.ok(message);
    }
    

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> handleSkillExists(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }


}
