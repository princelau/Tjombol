package com.example.tjombol.remote.Models;

public class TransactionModel {
    private String rAccount;
    private String amount;

    public TransactionModel(String rAccount, String amount){
        this.rAccount = rAccount;
        this.amount = amount;
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

}
