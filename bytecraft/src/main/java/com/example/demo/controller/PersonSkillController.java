package com.example.demo.controller;

import com.example.demo.dto.PersonSkillDTO;
import com.example.demo.entity.Skill;
import com.example.demo.service.PersonSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/person-skills")
public class PersonSkillController {

    @Autowired
    private PersonSkillService personSkillService;

    @GetMapping("/{id}")
    public ResponseEntity<List<PersonSkillDTO>> getPersonSkills(@PathVariable int id) {
        List<PersonSkillDTO> personSkills = personSkillService.getPersonSkillsByPersonId(id);
        return ResponseEntity.ok(personSkills);
    }

    @PostMapping("/{personId}/skills")
    public ResponseEntity<Void> addSkillToPerson(@PathVariable int personId, @RequestBody Skill skill) {
        personSkillService.addSkillToPerson(personId, skill);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
