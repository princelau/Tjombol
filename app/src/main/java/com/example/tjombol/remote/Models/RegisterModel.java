package com.example.tjombol.remote.Models;

import com.google.gson.annotations.SerializedName;

public class RegisterModel {

    private String companyId;

    @SerializedName("eNRIC")
    private String nRIC;

    @SerializedName("eId")
    private String id;

    @SerializedName("eName")
    private String name;

    @SerializedName("ePassword")
    private String email;

    @SerializedName("rAccount")
    private String account;

    private String mNumber;

    @SerializedName("pScheme")
    private String scheme;

    @SerializedName("ePassword")
    private String password;

    public RegisterModel(String companyId, String nRIC, String id, String name, String email, String account, String mNumber, String scheme, String password) {
        this.companyId = companyId;
        this.nRIC = nRIC;
        this.id = id;
        this.name = name;
        this.email = email;
        this.account = account;
        this.mNumber = mNumber;
        this.scheme = scheme;
        this.password = password;
    }
}