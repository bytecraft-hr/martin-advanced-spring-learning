
/* package com.example.demo;

import com.example.demo.entity.Person;
import com.example.demo.entity.Skill;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.SkillRepository; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private SkillRepository skillRepository; 

    @Override
    public void run(String... args) throws Exception {
        
        if (personRepository.count() == 0) {
            System.out.println("Inicijalizacija podataka za osobe...");
           personRepository.save(new Person("ivan.cikor@byte.eu", "ivan", "Cikor"));
            personRepository.save(new Person("martin.bcikor@byte.eu", "Ivana", "Cikor"));
            personRepository.save(new Person("marko.cikor@byte.eu", "marko", "Cikor"));
            System.out.println("Podaci za osobe su dodani.");
        } else {
            System.out.println("Podaci za osobe već postoje.");
        }

    
        if (skillRepository.count() == 0) {
            System.out.println("Inicijalizacija podataka za skill...");
            skillRepository.save(new Skill("Java"));
            skillRepository.save(new Skill("Spring Boot"));
            skillRepository.save(new Skill("Hibernate"));
            System.out.println("Podaci za skill su dodani.");
        } else {
            System.out.println("Podaci za skill već postoje.");
        }

        System.out.println("Inicijalizacija podataka završena.");
    }
}
 */