package com.example.demo.controller;

import com.example.demo.entity.Person;
import com.example.demo.service.PersonService;
import com.example.demo.dto.PersonDTO;
import com.example.demo.dto.PersonProjectDTO;
import com.example.demo.dto.PersonProjectDurationDTO;
import com.example.demo.dto.PersonProjectRankDTO;
import com.example.demo.dto.Project2DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/people")
public class PersonController {

    @Autowired
    private PersonService personService;
    
    @GetMapping
    public ResponseEntity<List<PersonProjectDTO>> getAllPeople() {
        List<PersonProjectDTO> peopleDTO = personService.getAllPeopleDTO();
        return ResponseEntity.ok(peopleDTO);
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<PersonProjectDTO> getPersonProjectDetails(@PathVariable("id") Integer id) {
        PersonProjectDTO personProjectDTO = personService.getPersonProjectDetails(id);
        return ResponseEntity.ok(personProjectDTO);
    }
    

  @PutMapping("/{id}")
public ResponseEntity<PersonDTO> updatePerson(@PathVariable("id") int id, @RequestBody Person updatedPerson) {
    try {
        Person person = personService.updatePerson(id, updatedPerson);
        PersonDTO personDTO = new PersonDTO(person); 
        return ResponseEntity.ok(personDTO);
    } catch (IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}




@DeleteMapping("/{id}")
public ResponseEntity<String> deletePerson(@PathVariable("id") int id) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    
    if (auth == null || auth.getAuthorities().stream()
            .noneMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
        return new ResponseEntity<>("Nemate ovlasti za brisanje ove osobe.", HttpStatus.FORBIDDEN);
    }

    personService.deletePerson(id);
    return ResponseEntity.noContent().build();
}

   

    @PostMapping
    public ResponseEntity<PersonDTO> createPerson(@RequestBody Person person) {
        try {
            Person savedPerson = personService.savePerson(person);
            PersonDTO personDTO = new PersonDTO(savedPerson);
            return ResponseEntity.status(HttpStatus.CREATED).body(personDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
  
@GetMapping("/email/{email}")
public ResponseEntity<PersonProjectDTO> getPersonByEmail(@PathVariable("email") String email) {
    Optional<PersonProjectDTO> personDTO = personService.getPersonDTOByEmail(email);
    return personDTO.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
}

    
@GetMapping("/skill/{skillName}")
public ResponseEntity<List<PersonProjectDTO>> getPeopleBySkillName(@PathVariable("skillName") String skillName) {
    List<PersonProjectDTO> peopleDTO = personService.getPeopleBySkillName(skillName);
    if (peopleDTO.isEmpty()) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(peopleDTO);
}
    

@GetMapping("/skills")
public ResponseEntity<List<PersonProjectDTO>> getPeopleBySkills(@RequestParam("skillNames") List<String> skillNames) {
    List<PersonProjectDTO> peopleDTO = personService.getPeopleBySkills(skillNames);
    if (peopleDTO.isEmpty()) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(peopleDTO);
}


@GetMapping("/skills-and-role")
public ResponseEntity<List<PersonProjectDTO>> getPeopleBySkillsAndRole(
        @RequestParam("skillNames") List<String> skillNames,
        @RequestParam("role") String role) {
    List<PersonProjectDTO> peopleDTO = personService.getPeopleBySkillsAndRole(skillNames, role);
    if (peopleDTO.isEmpty()) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(peopleDTO);
}
 

 @GetMapping("/person-project-percent-ranks")
    public List<PersonProjectRankDTO> getPersonProjectPercentRanks() {
        return personService.getPersonProjectPercentRanks();
    }


 @GetMapping("/projects2/participants")
public List<Project2DTO> getProjectsWithParticipants() {
    return personService.getProjectsWithParticipants();
}

  @GetMapping("/person-project-durations")
    public List<PersonProjectDurationDTO> getTotalDaysOnProjectsPerPerson() {
        return personService.getTotalDaysOnProjectsPerPerson();
    }


}
