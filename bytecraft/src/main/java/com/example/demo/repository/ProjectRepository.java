
package com.example.demo.repository;

import com.example.demo.entity.Project;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    Project findByProjectName(String projectName);



 @Query(value = "SELECT p.first_name, p.last_name, COUNT(pp.project_id) AS project_count, " +
                   "RANK() OVER (ORDER BY COUNT(pp.project_id) DESC) AS rank " +
                   "FROM person p " +
                   "JOIN person_project pp ON p.person_id = pp.person_id " +
                   "GROUP BY p.person_id, p.first_name, p.last_name",
           nativeQuery = true)
    List<Object[]> getPersonProjectRanks();


}

