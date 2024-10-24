CREATE TABLE app_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
);



-- Nova struktura kategorija
CREATE TABLE project_category (
    project_category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE skill_category (
    skill_category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL UNIQUE
);

-- Ostale tablice
CREATE TABLE person (
    person_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE project (
    project_id INT AUTO_INCREMENT PRIMARY KEY,
    project_name VARCHAR(100) NOT NULL,
    project_category_id INT NOT NULL,
    description TEXT,
    FOREIGN KEY (project_category_id) REFERENCES project_category(project_category_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE skill (
    skill_id INT AUTO_INCREMENT PRIMARY KEY,
    skill_name VARCHAR(100) NOT NULL,
    skill_category_id INT,
    FOREIGN KEY (skill_category_id) REFERENCES skill_category(skill_category_id) ON DELETE SET NULL
);

CREATE TABLE role (
    role_id INT AUTO_INCREMENT PRIMARY KEY,
    role VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE person_project (
    assignment_id INT AUTO_INCREMENT PRIMARY KEY,
    person_id INT,
    project_id INT,
    role_id INT,
    start_date DATE,
    end_date DATE,
    FOREIGN KEY (person_id) REFERENCES person(person_id) ON DELETE CASCADE,
    FOREIGN KEY (project_id) REFERENCES project(project_id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES role(role_id) ON DELETE CASCADE
);


CREATE TABLE person_skill (
    person_id INT,
    skill_id INT,
    PRIMARY KEY (person_id, skill_id),
    FOREIGN KEY (person_id) REFERENCES person(person_id) ON DELETE CASCADE,
    FOREIGN KEY (skill_id) REFERENCES skill(skill_id) ON DELETE CASCADE
);

-- Ubacivanje podataka
INSERT INTO role (role) VALUES ('Developer');
INSERT INTO role (role) VALUES ('Tester');
INSERT INTO role (role) VALUES ('Project Manager');

INSERT INTO project_category (category_name) VALUES ('Web Development');
INSERT INTO project_category (category_name) VALUES ('Mobile Development');
INSERT INTO project_category (category_name) VALUES ('Data');

INSERT INTO skill_category (category_name) VALUES ('Frontend');
INSERT INTO skill_category (category_name) VALUES ('Backend');
INSERT INTO skill_category (category_name) VALUES ('Database');

INSERT INTO person (first_name, last_name, email) VALUES ('Martin', 'Cikor', 'martin.cikor@bytecraft.eu');
INSERT INTO person (first_name, last_name, email) VALUES ('Angela', 'Radic', 'angela.radic@bytecraft.eu');
INSERT INTO person (first_name, last_name, email) VALUES ('Ivan', 'Horvat', 'ivan.horvat@example.com');
INSERT INTO person (first_name, last_name, email) VALUES ('Petra', 'Maric', 'petra.maric@example.com');
INSERT INTO person (first_name, last_name, email) VALUES ('Marko', 'Novak', 'marko.novak@example.com');
INSERT INTO person (first_name, last_name, email) VALUES ('Lucija', 'Kos', 'lucija.kos@example.com');
INSERT INTO person (first_name, last_name, email) VALUES ('Ante', 'Zovko', 'ante.zovko@example.com');
INSERT INTO person (first_name, last_name, email) VALUES ('Dora', 'Matic', 'dora.matic@example.com');


INSERT INTO skill (skill_name, skill_category_id) VALUES ('Programming', 2);
INSERT INTO skill (skill_name, skill_category_id) VALUES ('Java', 2);
INSERT INTO skill (skill_name, skill_category_id) VALUES ('Spring', 2);
INSERT INTO skill (skill_name, skill_category_id) VALUES ('Hibernate', 3);
INSERT INTO skill (skill_name, skill_category_id) VALUES ('Angular', 1);
INSERT INTO skill (skill_name, skill_category_id) VALUES ('Python', 3);
INSERT INTO skill (skill_name, skill_category_id) VALUES ('React', 1);
INSERT INTO skill (skill_name, skill_category_id) VALUES ('Kotlin', 2);
INSERT INTO skill (skill_name, skill_category_id) VALUES ('Django', 3);


INSERT INTO project (project_name, project_category_id, description) VALUES ('Programming in Java', 1, 'Making some projects in Java');
INSERT INTO project (project_name, project_category_id, description) VALUES ('Programming in Angular', 2, 'Making some projects in Angular');
INSERT INTO project (project_name, project_category_id, description) VALUES ('Programming in React', 3, 'Making some projects in React');
INSERT INTO project (project_name, project_category_id, description) VALUES ('Programming in Spring', 1, 'Making some projects in Spring');
INSERT INTO project (project_name, project_category_id, description) VALUES ('Programming in Hibernate', 2, 'Making some projects in Hibernate');
INSERT INTO project (project_name, project_category_id, description) VALUES ('Web App in Python', 1, 'Developing a Python web application');
INSERT INTO project (project_name, project_category_id, description) VALUES ('Mobile App in Kotlin', 2, 'Creating a mobile app with Kotlin');
INSERT INTO project (project_name, project_category_id, description) VALUES ('Data Analysis with Django', 3, 'Performing data analysis using Django');
INSERT INTO project (project_name, project_category_id, description) VALUES ('Make some EHEALTH project with SpringBoot', 3, 'Performing data analysis using SpringBoot');

INSERT INTO person_skill (person_id, skill_id) VALUES (1, 1);  -- Martin: Programming
INSERT INTO person_skill (person_id, skill_id) VALUES (1, 2);  -- Martin: Java
INSERT INTO person_skill (person_id, skill_id) VALUES (2, 2);  -- Angela: Java
INSERT INTO person_skill (person_id, skill_id) VALUES (3, 3);  -- Ivan: Spring
INSERT INTO person_skill (person_id, skill_id) VALUES (4, 4);  -- Petra: Hibernate
INSERT INTO person_skill (person_id, skill_id) VALUES (5, 5);  -- Marko: Angular
INSERT INTO person_skill (person_id, skill_id) VALUES (6, 6);  -- Lucija: Python
INSERT INTO person_skill (person_id, skill_id) VALUES (7, 7);  -- Ante: React
INSERT INTO person_skill (person_id, skill_id) VALUES (8, 8);  -- Dora: Kotlin
INSERT INTO person_skill (person_id, skill_id) VALUES (6, 9);  -- Lucija: Django


-- Dodavanje podataka s datumima u `person_project`
INSERT INTO person_project (person_id, project_id, role_id, start_date, end_date) VALUES (1, 1, 2, '2023-01-01', '2023-06-30');  -- Martin: Developer
INSERT INTO person_project (person_id, project_id, role_id, start_date, end_date) VALUES (2, 1, 2, '2023-02-01', '2023-08-31');  -- Angela: Tester
INSERT INTO person_project (person_id, project_id, role_id, start_date, end_date) VALUES (2, 3, 2, '2023-04-01', '2023-09-30');  -- Angela: Tester
INSERT INTO person_project (person_id, project_id, role_id, start_date, end_date) VALUES (2, 1, 3, '2023-04-01', '2023-09-30');  -- Angela: Project Manager

INSERT INTO person_project (person_id, project_id, role_id, start_date, end_date) VALUES (3, 3, 1, '2023-03-01', '2023-07-31');  -- Ivan: Developer
INSERT INTO person_project (person_id, project_id, role_id, start_date, end_date) VALUES (4, 1, 3, '2023-05-01', '2023-12-31');  -- Petra: Project Manager
INSERT INTO person_project (person_id, project_id, role_id, start_date, end_date) VALUES (5, 2, 1, '2023-06-01', '2023-11-30');  -- Marko: Developer
INSERT INTO person_project (person_id, project_id, role_id, start_date, end_date) VALUES (6, 6, 1, '2024-01-01', '2024-06-30');  -- Lucija na Python projektu kao Developer
INSERT INTO person_project (person_id, project_id, role_id, start_date, end_date) VALUES (7, 7, 2, '2024-02-01', '2024-07-31');  -- Ante na React projektu kao Tester
INSERT INTO person_project (person_id, project_id, role_id, start_date, end_date) VALUES (8, 8, 3, '2024-03-01', '2024-08-30');  -- Dora na Kotlin projektu kao Project Manager
INSERT INTO person_project (person_id, project_id, role_id, start_date, end_date) VALUES (6, 9, 1, '2024-04-01', '2024-09-30');  -- Lucija na Django projektu kao Developer


INSERT INTO app_user (username, password, role) VALUES ('user', '{noop}user', 'USER');
INSERT INTO app_user (username, password, role) VALUES ('admin', '{noop}admin', 'ADMIN');
