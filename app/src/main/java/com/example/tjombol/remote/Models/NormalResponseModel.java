package com.example.tjombol.remote.Models;

public class NormalResponseModel {

    private String msg;

    private String wbalance;

    public NormalResponseModel(String msg, String wbalance) {
        this.wbalance = wbalance;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getWbalance() {
        return wbalance;
    }

    public void setWbalance(String mbalance) {
        this.wbalance = mbalance;
    }
}
