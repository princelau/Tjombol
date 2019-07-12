package com.example.tjombol.db;

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

    @PrimaryKey
    @SerializedName("txId")
    private int transactionId;

    @SerializedName("txStatus")
    private String transactionStatus;
    private String sender;
    private String receiver;
    private String type;
    private int amount;
    private String date;
    private String comments;
    private int priority;

    public TxEntity(int transactionId, String transactionStatus, String sender, String receiver, String type, int amount, String date, String comments) {
        this.transactionId = transactionId;
        this.transactionStatus = transactionStatus;
        this.sender = sender;
        this.receiver = receiver;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.comments = comments;
        this.priority = transactionId;
        //this.txInfo = Arrays.asList(transactionStatus,sender,receiver,type,String.valueOf(amount),date,comment);
    }

    /*
    public int getId(){return id; }

    public void setId(int id) {
        this.id = id;
    }
    */

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
