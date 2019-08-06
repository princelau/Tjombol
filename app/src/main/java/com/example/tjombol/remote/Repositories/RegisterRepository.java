package com.example.tjombol.remote.Repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.tjombol.remote.ApiService;
import com.example.tjombol.remote.Models.RegisterModel;
import com.example.tjombol.remote.Models.RegisterResponseModel;
import com.example.tjombol.remote.Resource;
import com.example.tjombol.remote.RetrofitClient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterRepository {
    private MutableLiveData<Resource<RegisterResponseModel>> result = new MutableLiveData<>();
    private static RegisterRepository instance;
    private static final String TAG = "LoginRepository";


    public static RegisterRepository getInstance() {
        if (instance == null) {
            instance = new RegisterRepository();
        }
        return instance;
    }

    public MutableLiveData<Resource<RegisterResponseModel>> getRegisterData(String companyId, String nRIC, String name, String email, String account, String mNumber, String scheme, String password, String msalary) {
        Log.d(TAG, "getLoginViewModelMutableLiveData: email" + email);
        if (email != null || password != null) {
            ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
            RegisterModel registerModel = new RegisterModel(companyId, nRIC,name,email,account,mNumber,scheme,password, msalary);
            Call<RegisterResponseModel> registerCall = apiService.register(registerModel);
            registerCall.enqueue(new Callback<RegisterResponseModel>() {
                @Override
                public void onResponse(Call<RegisterResponseModel> call, Response<RegisterResponseModel> response) {
                    //progressBar.set(View.GONE);
                    if (response.isSuccessful() && response.body() != null) {
                        //result.setValue(Resource.success(response.body());
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
                public void onFailure(Call<RegisterResponseModel> call, Throwable t) {
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
