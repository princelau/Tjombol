package com.example.tjombol.remote.Models;

public class PlanModel {
    private String pDate;
    private String sAllocated;
    private int pStatus;

    public PlanModel(String pDate, String sAllocated, int pStatus) {
        this.pDate = pDate;
        this.sAllocated = sAllocated;
        this.pStatus = pStatus;
    }

    public String getpDate() {
        return pDate;
    }

    public void setpDate(String pDate) {
        this.pDate = pDate;
    }

    public String getsAllocated() {
        return sAllocated;
    }

    public void setsAllocated(String sAllocated) {
        this.sAllocated = sAllocated;
    }

    public int getpStatus() {
        return pStatus;
    }

    public void setpStatus(int pStatus) {
        this.pStatus = pStatus;
    }
}
