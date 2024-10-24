package com.example.demo.dto;

public class AuthenticationRequest {

    private String username;
    private String password;

    public AuthenticationRequest() {}

    // Konstruktor s argumentima
    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getteri i setteri
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

