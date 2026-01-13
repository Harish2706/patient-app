package com.pm.authservice.dto;

public class Loginresponsedto {
    private final String token;

    public Loginresponsedto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
