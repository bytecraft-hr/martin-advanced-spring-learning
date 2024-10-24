package com.example.demo.service;

import com.example.demo.entity.Skill;
import com.example.demo.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    private boolean skillExists(String skillName) {
        return !skillRepository.findBySkillName(skillName).isEmpty(); // Check if the list is not empty
    }

    public Skill saveSkill(Skill skill) {
        if (skillExists(skill.getSkillName())) {
            throw new RuntimeException("Skill already exists with name: " + skill.getSkillName());
        }
        return skillRepository.save(skill);
    }

    @Transactional
    public void deleteSkill(int skillId) {
        
        skillRepository.deleteById(skillId);
    }

    public Skill getSkillById(int skillId) {
        return skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found with id: " + skillId)); 
    }

    @Transactional
    public Skill updateSkill(int id, Skill updatedSkill) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found with id: " + id));
        
        skill.setSkillName(updatedSkill.getSkillName());
        return skillRepository.save(skill);
    }

    public List<Skill> findBySkillName(String skillName) {
        return skillRepository.findBySkillNameContainingIgnoreCase(skillName);
    }
}
