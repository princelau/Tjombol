package com.example.tjombol.remote.Models;

import com.google.gson.annotations.SerializedName;

// Received from the server
public class LoginResponseModel {

    @SerializedName("rAccount")
    private String userAccount;

    @SerializedName("mSalary")
    private String salary;

    private String eId;

    private String rate;

    private String companyName;

    private String token;

    public LoginResponseModel(String userAccount, String salary, String eId, String token, String rate, String companyName) {
        this.userAccount = userAccount;
        this.salary = salary;
        this.eId = eId;
        this.token = token;
        this.rate = rate;
        this.companyName = companyName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String geteId() {
        return eId;
    }

    public void seteId(String eId) {
        this.eId = eId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
