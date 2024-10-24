package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.AppUser;


public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}


