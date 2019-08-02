package com.example.tjombol.remote.Models;

public class UserInfoResponseModel {

    private String companyId;
    private String eNRIC;
    private String eId;
    private String eName;
    private String eEmail;
    private String mNumber;
    private String pScheme;
    private String mSalary;
    private String wBalance;
    private String eDateCreated;


    public UserInfoResponseModel(String companyId, String eNRIC, String eId, String eName, String eEmail, String mNumber, String pScheme, String mSalary, String wBalance, String eDateCreated) {
        this.companyId = companyId;
        this.eNRIC = eNRIC;
        this.eId = eId;
        this.eName = eName;
        this.eEmail = eEmail;
        this.mNumber = mNumber;
        this.pScheme = pScheme;
        this.mSalary = mSalary;
        this.wBalance = wBalance;
        this.eDateCreated = eDateCreated;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String geteNRIC() {
        return eNRIC;
    }

    public void seteNRIC(String eNRIC) {
        this.eNRIC = eNRIC;
    }

    public String geteId() {
        return eId;
    }

    public String getmSalary() {
        return mSalary;
    }

    public void setmSalary(String mSalary) {
        this.mSalary = mSalary;
    }

    public void seteId(String eId) {
        this.eId = eId;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String geteEmail() {
        return eEmail;
    }

    public void seteEmail(String eEmail) {
        this.eEmail = eEmail;
    }

    public String getmNumber() {
        return mNumber;
    }

    public void setmNumber(String mNumber) {
        this.mNumber = mNumber;
    }

    public String getpScheme() {
        return pScheme;
    }

    public void setpScheme(String pScheme) {
        this.pScheme = pScheme;
    }

    public String getwBalance() {
        return wBalance;
    }

    public void setwBalance(String wBalance) {
        this.wBalance = wBalance;
    }

    public String geteDateCreated() {
        return eDateCreated;
    }

    public void seteDateCreated(String eDateCreated) {
        this.eDateCreated = eDateCreated;
    }
}
