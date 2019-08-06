package com.example.tjombol.remote;

import android.util.Log;

public class UserConstant {
    private static final String TAG = "UserConstant";
    private static String user_account;
    private static String user_salary;
    private static String user_eId;
    private static String user_rate;
    private static String company_Name;
    private static UserConstant instance;

    public synchronized static UserConstant getInstance() {
        if(instance == null) {
            instance = new UserConstant();
        }
        return instance;
    }
    public void setUser_account(String new_user_account,String new_user_salary,String new_user_eId, String new_user_rate, String new_company_name) {
        user_account = new_user_account;
        user_salary = new_user_salary;
        user_eId = new_user_eId;
        user_rate = new_user_rate;
        company_Name = new_company_name;
        Log.d(TAG, "setUser_account: "+user_account);
        Log.d(TAG, "setUser_salary: "+user_salary);
        Log.d(TAG, "setUser_eId: "+user_eId);
        Log.d(TAG, "setUser_rate: "+user_rate);
        Log.d(TAG, "setComp_name: "+company_Name);
    }

    public String getUser_account() {
        return user_account;
    }

    public String getUser_eId() {
        return user_eId;
    }

    public String getUser_salary() {
        return user_salary;
    }

    public String getUser_rate() {
        return user_rate;
    }

    public String getCompany_Name() {
        return company_Name;
    }
}
