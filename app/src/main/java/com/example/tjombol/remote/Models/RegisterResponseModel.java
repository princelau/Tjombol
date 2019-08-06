package com.example.tjombol.remote.Models;

public class RegisterResponseModel {
    private String token;

    public RegisterResponseModel(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
