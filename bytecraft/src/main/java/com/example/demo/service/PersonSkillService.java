package com.example.demo.service;

import com.example.demo.dto.PersonSkillDTO;
import com.example.demo.entity.Person;
import com.example.demo.entity.Skill;
import com.example.demo.repository.PersonSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonSkillService {

    @Autowired
    private PersonSkillRepository personSkillRepository;

    public List<PersonSkillDTO> getPersonSkillsByPersonId(int personId) {
        Person person = personSkillRepository.findById(personId)
                .orElseThrow(() -> new RuntimeException("Person not found with id: " + personId));
        
                return person.getSkills().stream().map(skill -> {
                    String skillCategoryName = skill.getSkillCategory() != null ? skill.getSkillCategory().getCategoryName() : null;
                    return new PersonSkillDTO(
                        person.getFirstName(), 
                        person.getLastName(), 
                        person.getEmail(), 
                        skillCategoryName, 
                        skill.getSkillName()
                    );
                }).collect(Collectors.toList());
                
                
    }



    public void addSkillToPerson(int personId, Skill skill) {
        Person person = personSkillRepository.findById(personId)
                .orElseThrow(() -> new RuntimeException("Person not found with id: " + personId));
        
        person.getSkills().add(skill);
        personSkillRepository.save(person);
    }
}
