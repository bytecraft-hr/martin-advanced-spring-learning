package com.example.demo.dto;
public class SkillDTO {
    private String skillName;
    private String skillCategory;

    public SkillDTO(String skillName, String skillCategory) {
        this.skillName = skillName;
        this.skillCategory = skillCategory;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillCategory() {
        return skillCategory;
    }

    public void setSkillCategory(String skillCategory) {
        this.skillCategory = skillCategory;
    }
}

