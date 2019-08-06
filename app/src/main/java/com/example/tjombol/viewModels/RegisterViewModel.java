package com.example.tjombol.viewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tjombol.remote.Models.RegisterResponseModel;
import com.example.tjombol.remote.Repositories.RegisterRepository;
import com.example.tjombol.remote.Resource;

public class RegisterViewModel extends ViewModel {
    private static final String TAG = "RegisterViewModel";
    private RegisterRepository registerRepository;


    public void init(){
        Log.d(TAG, "init: is called");
        registerRepository = RegisterRepository.getInstance();
    }


    // LiveData
    public LiveData<Resource<RegisterResponseModel>> getRegisterDataObservable(String companyId, String nRIC, String name, String email, String account, String mNumber, String scheme, String password, String mSalary) {
        Log.d(TAG, "getUserDataObservable: is called");
        return registerRepository.getRegisterData(companyId,nRIC,name,email, account, mNumber, scheme, password, mSalary);
    }
}
