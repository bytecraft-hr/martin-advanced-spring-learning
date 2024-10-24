package com.example.demo.controller;

import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.entity.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private CustomUserDetailsService userService;

    @PostMapping("/register")
    public ResponseEntity<AppUser> register(@RequestBody AppUser user) {
        // Automatski postavljamo ulogu USER za sve registracije
        user.setRole("USER");
    
        // Pozivamo servisnu metodu za registraciju
        AppUser savedUser = userService.registerNewUser(user);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
    

    @GetMapping("/login/succesfull")
    public String successfulLogin() {
        return "Prijava je uspješna! Dobrodošli na početnu stranicu!";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "logout", required = false) String logout) {
        if (logout != null) {
            return "Uspješno ste se odjavili.";
        }
        return "Molimo prijavite se.";
    }


    @GetMapping("/auth/logout-message")
    public String logoutMessage() {
        return "Uspješno ste se odjavili.";
    }

}
