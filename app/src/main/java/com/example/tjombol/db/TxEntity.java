package com.example.tjombol.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

@Entity(tableName = "tx_table")
public class TxEntity {

    //@PrimaryKey(autoGenerate = true)
    //private int id;
    @NonNull
    @PrimaryKey
    @SerializedName("txId")
    private String transactionId;

    @SerializedName("txStatus")
    private int transactionStatus;

    @SerializedName("oAccount")
    private String sender;

    private String receiver;

    private String amount;

    @SerializedName("pDateTime")
    private String date;

    @SerializedName("pPurpose")
    private String comments;

    private String priority;

    public TxEntity(String transactionId, int transactionStatus, String sender, String receiver, String amount, String date, String comments) {
        this.transactionId = transactionId;
        this.transactionStatus = transactionStatus;
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.date = date;
        this.comments = comments;
        this.priority = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public int getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(int transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comment) {
        this.comments = comments;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

}
