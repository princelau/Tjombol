package com.example.tjombol.remote.Repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.tjombol.remote.ApiService;
import com.example.tjombol.remote.Models.LoginModel;
import com.example.tjombol.remote.Models.LoginResponseModel;
import com.example.tjombol.remote.Resource;
import com.example.tjombol.remote.RetrofitClient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {
    private MutableLiveData<Resource<LoginResponseModel>> result = new MutableLiveData<>();
    private static LoginRepository instance;
    private static final String TAG = "LoginRepository";


    public static LoginRepository getInstance() {
        if (instance == null) {
            instance = new LoginRepository();
        }
        return instance;
    }

    public MutableLiveData<Resource<LoginResponseModel>> getLoginViewModelMutableLiveData(String email, String password) {
        Log.d(TAG, "getLoginViewModelMutableLiveData: email" + email);
        if (email != null || password != null) {
            ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
            LoginModel loginModel = new LoginModel(email, password);
            Call<LoginResponseModel> loginResponse = apiService.login(loginModel);
            loginResponse.enqueue(new Callback<LoginResponseModel>() {
                @Override
                public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                    //progressBar.set(View.GONE);
                    if (response.isSuccessful() && response.body() != null) {
                        Log.d(TAG, String.format("onResponse: user account: %s \n token: %s",
                                response.body().getUserAccount(), response.body().getToken()));
                        result.setValue(Resource.success(response.body()));
                    } else {
                        try {
                            if (response.code() == 400) {

                                if (response.errorBody() != null) {
                                    //response is cleared after being called
                                    result.setValue(Resource.error(response.errorBody().string(), null));
                                    //Log.d(TAG, "onResponse: "+result.getValue().getMessage());
                                } else {
                                    result.setValue(Resource.error("Bad Request", null));
                                }
                            } else {
                                if (response.errorBody() != null) {
                                    //Log.d(TAG, "onResponse: you are in deep deep shit"
                                            //+ response.code() + ": " + response.errorBody().string());
                                    result.setValue(Resource.error(response.errorBody().string(), null));
                                    //Log.d(TAG, "onResponse: "+result.getValue().getMessage());
                                } else {
                                    result.setValue(Resource.error("unknown error", null));
                                }
                            }
                        } catch (IOException e) {
                            Log.d(TAG, "unknown error");
                            result.setValue(Resource.error("unknown error", null));
                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t.getMessage());
                    //LoginResponseModel loginResponseModel = new LoginResponseModel(null, null);
                    //loginViewModelMutableLiveData.setValue(loginResponseModel);
                    result.setValue(Resource.error(t.getMessage(), null));
                }
            });
        } else {
            Log.d(TAG, "getLoginViewModelMutableLiveData: initial call");
            result.setValue(Resource.initializing("initial call", null));
        }

        return result;
    }
}
