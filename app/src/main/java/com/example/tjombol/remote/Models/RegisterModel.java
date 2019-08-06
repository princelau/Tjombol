package com.example.tjombol.remote.Models;

import com.google.gson.annotations.SerializedName;

public class RegisterModel {

    private String companyId;

    @SerializedName("eNRIC")
    private String nRIC;

    @SerializedName("eName")
    private String name;

    @SerializedName("eEmail")
    private String email;

    @SerializedName("rAccount")
    private String account;

    private String mNumber;

    private String mSalary;

    @SerializedName("pScheme")
    private String scheme;

    @SerializedName("ePassword")
    private String password;

    public RegisterModel(String companyId, String nRIC, String name, String email, String account, String mNumber, String scheme, String password, String mSalary) {
        this.companyId = companyId;
        this.nRIC = nRIC;
        this.name = name;
        this.email = email;
        this.account = account;
        this.mNumber = mNumber;
        this.scheme = scheme;
        this.password = password;
        this.mSalary = mSalary;
    }
}