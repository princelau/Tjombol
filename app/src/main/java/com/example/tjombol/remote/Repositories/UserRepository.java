package com.example.tjombol.remote.Repositories;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;

import com.example.tjombol.remote.ApiService;
import com.example.tjombol.remote.Models.UserInfoResponseModel;
import com.example.tjombol.remote.Resource;
import com.example.tjombol.remote.RetrofitClient;

import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
        private MutableLiveData<Resource<UserInfoResponseModel>> result = new MutableLiveData<>();
        private static UserRepository instance;
        private static final String TAG = "UserRepository";

        public static UserRepository getInstance() {
            if (instance == null) {
                instance = new UserRepository();
            }
            return instance;
        }

        public MutableLiveData<Resource<UserInfoResponseModel>> getUserRepositoryInfo(String userAccount) {
                ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
                Call<UserInfoResponseModel> userInfoResponse = apiService.getUserInfo(userAccount);
                userInfoResponse.enqueue(new Callback<UserInfoResponseModel>() {
                    @Override
                    public void onResponse(Call<UserInfoResponseModel> call, Response<UserInfoResponseModel> response) {
                        //progressBar.set(View.GONE);
                        if (response.isSuccessful() && response.body() != null) {
                            //Log.d(TAG, String.format("onResponse: user account: %s \n token: %s",
                                    //response.body().getUserAccount(), response.body().getToken()));
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
                    public void onFailure(Call<UserInfoResponseModel> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t.getMessage());
                        //LoginResponseModel loginResponseModel = new LoginResponseModel(null, null);
                        //loginViewModelMutableLiveData.setValue(loginResponseModel);
                        result.setValue(Resource.error(t.getMessage(), null));
                    }
                });
            return result;
        }
    }
