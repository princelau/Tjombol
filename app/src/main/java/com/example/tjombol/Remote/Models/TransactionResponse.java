package com.example.tjombol.Remote.Models;

import com.example.tjombol.DB.Entities.TxEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransactionResponse {

    @SerializedName("results")
    private List<TxEntity> transactions;

    /**
     * This method return the list of transaction entities
     * @return List of transaction entities
     */
    public List<TxEntity> getTransactions() {
        return transactions;
    }

    /**
     * This method sets the article entities
     * @param transactions - transaction list
     */
    @SuppressWarnings("unused")
    public void setTransactions(List<TxEntity> transactions) {
        this.transactions = transactions;
    }

}