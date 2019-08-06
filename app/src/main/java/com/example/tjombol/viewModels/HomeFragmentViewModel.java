package com.example.tjombol.viewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tjombol.remote.Models.UserInfoResponseModel;
import com.example.tjombol.remote.Repositories.UserRepository;
import com.example.tjombol.remote.Resource;

public class HomeFragmentViewModel extends ViewModel {

    private static final String TAG = "HomeFragmentViewModel";
    private UserRepository userRepository;

    public void init(){
        Log.d(TAG, "init: is called");
        userRepository = UserRepository.getInstance();
    }

    // LiveData
    public LiveData<Resource<UserInfoResponseModel>> getUserInfoObservable(String userAccount) {
        Log.d(TAG, "getUserDataObservable: is called");
        return userRepository.getUserRepositoryInfo(userAccount);
    }
}
