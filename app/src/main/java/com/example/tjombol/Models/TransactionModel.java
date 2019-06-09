package com.example.tjombol.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class TransactionModel {

    @SerializedName("txId")
    private String transactionId;

    @SerializedName("txStatus")
    private String transactionStatus;

    private String sender;
    private String receiver;
    private String type;
    private int amount;
    private String date;
    private String comment;


    public String getTransactionId() {
        return transactionId;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getComment() {
        return comment;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }
}