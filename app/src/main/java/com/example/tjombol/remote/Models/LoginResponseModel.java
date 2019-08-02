package com.example.tjombol.remote.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;
// Received from the server
public class LoginResponseModel {

    @SerializedName("rAccount")
    private String userAccount;

    private String token;

    public LoginResponseModel(String userAccount, String token){
        this.userAccount = userAccount;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
}
