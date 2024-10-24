package com.example.demo.repository;

import com.example.demo.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Person findByEmail(String email);
    List<Person> findBySkillsSkillName(String skillName);
    List<Person> findBySkillsSkillCategoryCategoryName(String categoryName);
    List<Person> findBySkillsSkillNameAndSkillsSkillCategoryCategoryName(String skillName, String categoryName);
    List<Person> findBySkillsSkillNameIn(List<String> skillNames);


    // Find persons by skill category
    @Query("SELECT p FROM Person p JOIN p.skills s WHERE s.skillCategory = :skillCategory")
    List<Person> findBySkillCategory(@Param("skillCategory") String skillCategory);

    // Find persons by skill name
    @Query("SELECT p FROM Person p JOIN p.skills s WHERE s.skillName = :skillName")
    List<Person> findBySkillName(@Param("skillName") String skillName);


    @Query("SELECT p FROM Person p JOIN p.skills s JOIN p.personProjects pp JOIN pp.role r " +
    "WHERE s.skillName IN :skillNames AND r.role = :role")
List<Person> findPeopleBySkillsAndRole(@Param("skillNames") List<String> skillNames, @Param("role") String role);

    
@Query(value = "SELECT p.first_name, p.last_name, COUNT(pp.project_id) AS project_count, " +
               "PERCENT_RANK() OVER (ORDER BY COUNT(pp.project_id) DESC) AS percent_rank " +
               "FROM person p " +
               "JOIN person_project pp ON p.person_id = pp.person_id " +
               "GROUP BY p.person_id, p.first_name, p.last_name " +
               "ORDER BY percent_rank ASC, project_count DESC",
       nativeQuery = true)
List<Object[]> getPersonProjectPercentRanks();



    @Query(value = "SELECT p.project_name, per.first_name, per.last_name, r.role " +
                   "FROM project p " +
                   "JOIN person_project pp ON p.project_id = pp.project_id " +
                   "JOIN person per ON pp.person_id = per.person_id " +
                   "JOIN role r ON pp.role_id = r.role_id " +
                   "ORDER BY p.project_name, per.last_name, per.first_name",
           nativeQuery = true)
  
          

    List<Object[]> getProjectParticipantsWithRoles();





    @Query(value = "SELECT p.first_name, p.last_name, " +
    "SUM(DATEDIFF('DAY', pp.start_date, pp.end_date)) AS total_days_on_projects " +
    "FROM person p " +
    "JOIN person_project pp ON p.person_id = pp.person_id " +
    "GROUP BY p.person_id, p.first_name, p.last_name",
nativeQuery = true)
List<Object[]> getTotalDaysOnProjectsPerPerson();


}







