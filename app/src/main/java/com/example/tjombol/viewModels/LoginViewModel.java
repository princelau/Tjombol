package com.example.tjombol.viewModels;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tjombol.remote.Models.LoginResponseModel;
import com.example.tjombol.remote.Repositories.LoginRepository;
import com.example.tjombol.remote.Resource;


public class LoginViewModel extends ViewModel {

    private static final String TAG = "LoginViewModel";
    private LoginRepository loginRepository;


    public void init(){
        Log.d(TAG, "init: is called");
        loginRepository = LoginRepository.getInstance();
    }


    // LiveData
    public LiveData<Resource<LoginResponseModel>> getUserDataObservable(String email, String password) {
        Log.d(TAG, "getUserDataObservable: is called");
        return loginRepository.getLoginViewModelMutableLiveData(email,password);
    }
}
