package com.example.demo.dto;


public class AuthenticationResponse {

    private String jwt;

    // Konstruktor
    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    // Getter za JWT
    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}

