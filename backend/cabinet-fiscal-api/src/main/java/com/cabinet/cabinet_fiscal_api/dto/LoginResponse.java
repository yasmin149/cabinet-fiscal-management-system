package com.cabinet.cabinet_fiscal_api.dto;

public class LoginResponse {

    private String token;

    public LoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}