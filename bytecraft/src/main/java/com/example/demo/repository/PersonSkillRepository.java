package com.example.demo.repository;

import com.example.demo.entity.Person;
import com.example.demo.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PersonSkillRepository extends JpaRepository<Person, Integer> {
    List<Skill> findSkillsByPersonId(int personId);


}

