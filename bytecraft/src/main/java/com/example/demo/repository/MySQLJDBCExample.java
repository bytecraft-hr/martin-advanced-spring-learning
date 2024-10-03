package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

@Component
public class MySQLJDBCExample implements CommandLineRunner {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;

    @Override
    public void run(String... args) throws Exception {
        Connection connection = null;



        
        try {
            // loadamo driver jdbc
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);

            // dodavanje osobe
            List<String> skills = Arrays.asList("Java", "SQL");
            List<String> projects = Arrays.asList("Project A", "Project B");
            addPerson(connection, "John", "Doe", "john.doe@example.com", skills, projects);
            addPerson(connection, "IVANO", "test", "ivano@gmail.com", skills, projects);

            // update osobe
            List<String> newSkills = Arrays.asList("Python", "JavaScript");
            List<String> newProjects = Arrays.asList("Project C", "this is project named C");
            updatePerson(connection, 2, "John", "Smith", "john.smith@example.com", newSkills, newProjects);

            // brisanje osobe
            deletePerson(connection, 9);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




public static void addPerson(Connection connection, String firstName, String lastName, String email, List<String> skills, List<String> projects) {
    try {
        // insert u people
        String personSql = "INSERT INTO people (first_name, last_name, email) VALUES (?, ?, ?)";
        PreparedStatement personStatement = connection.prepareStatement(personSql, Statement.RETURN_GENERATED_KEYS);
        personStatement.setString(1, firstName);
        personStatement.setString(2, lastName);
        personStatement.setString(3, email);
        personStatement.executeUpdate();

     
        ResultSet personKeys = personStatement.getGeneratedKeys();
        int personId = 0;
        if (personKeys.next()) {
            personId = personKeys.getInt(1);
        }

        // Insertamo skil
        for (String skill : skills) {
            addSkillToPerson(connection, personId, skill);
        }

        // Insertamo projects
        for (String project : projects) {
            addProjectToPerson(connection, personId, project);
        }

        System.out.println("Person, skills, and projects added successfully.");
    } catch (Exception e) {
        e.printStackTrace();
    }
}

private static void addSkillToPerson(Connection connection, int personId, String skillName) {
    try {
        // provjera da li skill postoji, ako ne postoji insert
        String skillSql = "INSERT INTO skills (skill_name) VALUES (?) ON DUPLICATE KEY UPDATE skill_id=LAST_INSERT_ID(skill_id)";
        PreparedStatement skillStatement = connection.prepareStatement(skillSql, Statement.RETURN_GENERATED_KEYS);
        skillStatement.setString(1, skillName);
        skillStatement.executeUpdate();

        // Get skill_id
        ResultSet skillKeys = skillStatement.getGeneratedKeys();
        int skillId = 0;
        if (skillKeys.next()) {
            skillId = skillKeys.getInt(1);
        }

        //  insert u people_skills
        String peopleSkillSql = "INSERT INTO people_skills (person_id, skill_id) VALUES (?, ?)";
        PreparedStatement peopleSkillStatement = connection.prepareStatement(peopleSkillSql);
        peopleSkillStatement.setInt(1, personId);
        peopleSkillStatement.setInt(2, skillId);
        peopleSkillStatement.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

private static void addProjectToPerson(Connection connection, int personId, String projectName) {
    try {
        // provjera da li project postoji, ako ne postoji insert
        String projectSql = "INSERT INTO projects (project_name) VALUES (?) ON DUPLICATE KEY UPDATE project_id=LAST_INSERT_ID(project_id)";
        PreparedStatement projectStatement = connection.prepareStatement(projectSql, Statement.RETURN_GENERATED_KEYS);
        projectStatement.setString(1, projectName);
        projectStatement.executeUpdate();

        // Get project_id
        ResultSet projectKeys = projectStatement.getGeneratedKeys();
        int projectId = 0;
        if (projectKeys.next()) {
            projectId = projectKeys.getInt(1);
        }

        // Insert into People_Projects
        String peopleProjectSql = "INSERT INTO people_projects (person_id, project_id) VALUES (?, ?)";
        PreparedStatement peopleProjectStatement = connection.prepareStatement(peopleProjectSql);
        peopleProjectStatement.setInt(1, personId);
        peopleProjectStatement.setInt(2, projectId);
        peopleProjectStatement.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}





public static void updatePerson(Connection connection, int personId, String newFirstName, String newLastName, String newEmail, List<String> newSkills, List<String> newProjects) {
    try {
        // update people ime prezime email
        String personSql = "UPDATE people SET first_name = ?, last_name = ?, email = ? WHERE person_id = ?";
        PreparedStatement personStatement = connection.prepareStatement(personSql);
        personStatement.setString(1, newFirstName);
        personStatement.setString(2, newLastName);
        personStatement.setString(3, newEmail);
        personStatement.setInt(4, personId);
        personStatement.executeUpdate();

        // Remove old skills
        String deleteSkillsSql = "DELETE FROM people_skills WHERE person_id = ?";
        PreparedStatement deleteSkillsStatement = connection.prepareStatement(deleteSkillsSql);
        deleteSkillsStatement.setInt(1, personId);
        deleteSkillsStatement.executeUpdate();

        // Reinsert new skills
        for (String skill : newSkills) {
            addSkillToPerson(connection, personId, skill);
        }

        // Remove old projects
        String deleteProjectsSql = "DELETE FROM people_projects WHERE person_id = ?";
        PreparedStatement deleteProjectsStatement = connection.prepareStatement(deleteProjectsSql);
        deleteProjectsStatement.setInt(1, personId);
        deleteProjectsStatement.executeUpdate();

        // Reinsert new projects
        for (String project : newProjects) {
            addProjectToPerson(connection, personId, project);
        }

        System.out.println("Person, skills, and projects updated successfully.");
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public static void deletePerson(Connection connection, int personId) {
    try {
        // brisanje iz people po id
        String deletePersonSql = "DELETE FROM people WHERE person_id = ?";
        PreparedStatement deletePersonStatement = connection.prepareStatement(deletePersonSql);
        deletePersonStatement.setInt(1, personId);
        deletePersonStatement.executeUpdate();

        System.out.println("Person and associated skills/projects deleted successfully.");
    } catch (Exception e) {
        e.printStackTrace();
    }
}



    
}
