package com.example.tjombol.remote.Models;

import java.io.Serializable;

public class TxSer implements Serializable {

    private String transactionId;

    private int transactionStatus;

    private String sender;

    private String receiver;

    private String amount;

    private String date;

    private String comments;

    private String priority;

    public TxSer(String transactionId, int transactionStatus, String sender, String receiver, String amount, String date, String comments) {
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
