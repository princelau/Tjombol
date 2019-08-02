package com.example.tjombol.remote.Models;

import com.google.gson.annotations.SerializedName;
// Send to the server
public class LoginModel {

    @SerializedName("eEmail")
    private String email;

    @SerializedName("ePassword")
    private String password;

    public LoginModel(String email, String password){
        this.email = email;
        this.password = password;
    }
}
