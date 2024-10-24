package com.example.demo.service;

import com.example.demo.dto.PersonProjectRankDTO;
import java.util.ArrayList;
import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;
import com.example.demo.dto.PersonProjectDTO;
import com.example.demo.dto.PersonProjectDurationDTO;
import com.example.demo.dto.Project2DTO;
import com.example.demo.dto.ProjectDTO;
import com.example.demo.dto.ProjectParticipantDTO;
import com.example.demo.dto.SkillDTO;
import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired

    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonProjectDTO> getAllPeopleDTO() {
        List<Person> people = personRepository.findAll();
        return people.stream()
                     .map(this::convertToPersonProjectDTO)
                     .collect(Collectors.toList());
    }

    public List<PersonProjectDTO> getPeopleBySkills(List<String> skillNames) {
        // Metoda u personRepository koja podržava pronalaženje osoba po listi veština
        List<Person> people = personRepository.findBySkillsSkillNameIn(skillNames);
        return people.stream()
                     .map(this::convertToPersonProjectDTO)
                     .collect(Collectors.toList());
    }
    

    @Transactional
    public Person updatePerson(int id, Person updatedPerson) {
        Person existingPerson = personRepository.findById(id)
                                .orElseThrow(() -> new IllegalArgumentException("Person not found"));
    
        existingPerson.setFirstName(updatedPerson.getFirstName());
        existingPerson.setLastName(updatedPerson.getLastName());
        existingPerson.setEmail(updatedPerson.getEmail());
    
        return personRepository.save(existingPerson);
    }

    public void deletePerson(int id) {
        personRepository.deleteById(id);
    }

    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

 

 
    public PersonProjectDTO getPersonProjectDetails(Integer personId) {
        Person person = personRepository.findById(personId)
            .orElseThrow(() -> new RuntimeException("Person not found"));

        return convertToPersonProjectDTO(person);
    }
 

    private PersonProjectDTO convertToPersonProjectDTO(Person person) {
        List<ProjectDTO> projectDTOs = person.getPersonProjects().stream()
            .map(pp -> new ProjectDTO(
                pp.getProject().getProjectName(),
                pp.getRole() != null ? pp.getRole().getRole() : "No Role",
                pp.getStartDate(),
                pp.getEndDate()
            ))
            .collect(Collectors.toList());

        List<SkillDTO> skillDTOs = person.getSkills().stream()
            .map(skill -> new SkillDTO(skill.getSkillName(), skill.getSkillCategory().getCategoryName()))
            .collect(Collectors.toList());

        return new PersonProjectDTO(
            person.getFirstName(),
            person.getLastName(),
            person.getEmail(),
            projectDTOs,
            skillDTOs
        );
    }

public Person findUserByEmail(String email) throws Exception {
    Person person = personRepository.findByEmail(email);
    if (person == null) {
        throw new Exception("User not found with email: " + email);
    }
    return person;
}


 public Optional<PersonProjectDTO> getPersonDTOById(int id) {
    return personRepository.findById(id)
                           .map(this::convertToPersonProjectDTO);
}



public Optional<PersonProjectDTO> getPersonDTOByEmail(String email) {
    return Optional.ofNullable(personRepository.findByEmail(email))
                   .map(this::convertToPersonProjectDTO);
}






public List<PersonProjectDTO> getPeopleBySkillName(String skillName) {
    return personRepository.findBySkillsSkillName(skillName)
                           .stream()
                           .map(this::convertToPersonProjectDTO)
                           .collect(Collectors.toList());
}


public List<PersonProjectDTO> getPeopleBySkillsAndRole(List<String> skillNames, String role) {
    List<Person> people = personRepository.findPeopleBySkillsAndRole(skillNames, role);
    return people.stream().map(this::convertToPersonProjectDTO).collect(Collectors.toList());
}

public List<PersonProjectRankDTO> getPersonProjectPercentRanks() {
    List<Object[]> results = personRepository.getPersonProjectPercentRanks();
    List<PersonProjectRankDTO> ranks = new ArrayList<>();

    for (Object[] row : results) {
        PersonProjectRankDTO dto = new PersonProjectRankDTO();
        dto.setFirstName((String) row[0]);
        dto.setLastName((String) row[1]);
        dto.setProjectCount(((Number) row[2]).intValue());
        dto.setPercentRank(((Number) row[3]).doubleValue());
        ranks.add(dto);
    }
    return ranks;
}


public List<Project2DTO> getProjectsWithParticipants() {
    List<Object[]> results = personRepository.getProjectParticipantsWithRoles();
    Map<String, List<ProjectParticipantDTO>> projectMap = new HashMap<>();

    for (Object[] row : results) {
        String projectName = (String) row[0];
        String firstName = (String) row[1];
        String lastName = (String) row[2];
        String role = (String) row[3];

        projectMap.putIfAbsent(projectName, new ArrayList<>());
        projectMap.get(projectName).add(new ProjectParticipantDTO(firstName, lastName, role));
    }

    List<Project2DTO> projects = new ArrayList<>();
    projectMap.forEach((name, participants) -> {
        projects.add(new Project2DTO(name, participants));
    });

    return projects;
}



 public List<PersonProjectDurationDTO> getTotalDaysOnProjectsPerPerson() {
        List<Object[]> results = personRepository.getTotalDaysOnProjectsPerPerson();
        List<PersonProjectDurationDTO> durations = new ArrayList<>();

        for (Object[] row : results) {
            String firstName = (String) row[0];
            String lastName = (String) row[1];
            int totalDays = ((Number) row[2]).intValue();
            durations.add(new PersonProjectDurationDTO(firstName, lastName, totalDays));
        }

        return durations;
    }


}
