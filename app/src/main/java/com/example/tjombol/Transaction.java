package com.example.tjombol;

public class Transaction {

    private String id;
    private String sender;
    private String receiver;
    private String type;
    private int amount;
    private String date;
    private String comment;
    private String status;

    public Transaction() {
    }

    public Transaction(String id, String sender, String receiver, String type,  int amount, String date, String comment, String status) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.comment = comment;
        this.status = status;
    }


    public String getId()
    {
        return id;
    }

    public String getSender() { return sender; }

    public String getReceiver() { return receiver; }

    public String getType() { return type; }

    public int getAmount() { return amount; }

    public String getDate()
    {
        return date;
    }

    public String getComment()
    {
        return comment;
    }

    public String getStatus() { return status; }
}
