package com.example.tjombol.remote.Models;

public class TransactionModel {
    private String rAccount;
    private String amount;
    private String pPurpose;

    public TransactionModel(String rAccount, String amount, String pPurpose) {
        this.rAccount = rAccount;
        this.amount = amount;
        this.pPurpose = pPurpose;
    }

    public String getrAccount() {
        return rAccount;
    }

    public void setrAccount(String rAccount) {
        this.rAccount = rAccount;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getpPurpose() {
        return pPurpose;
    }

    public void setpPurpose(String pPurpose) {
        this.pPurpose = pPurpose;
    }
}
