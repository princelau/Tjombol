package com.example.tjombol.remote;

import com.example.tjombol.db.TxEntity;
import com.example.tjombol.remote.Models.LoginModel;
import com.example.tjombol.remote.Models.NormalResponseModel;
import com.example.tjombol.remote.Models.RegisterModel;
import com.example.tjombol.remote.Models.LoginResponseModel;
import com.example.tjombol.remote.Models.TransactionModel;
import com.example.tjombol.remote.Models.UserInfoResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    // get
    @GET("users/api/txOf")
    Call<List<TxEntity>> getTransactions(@Query("rAccount")String userAccount);

    @GET("users/api")
    Call<UserInfoResponseModel> getUserInfo(@Query("rAccount")String userAccount);

    // post
    @POST("users/api/login")
    Call<LoginResponseModel> login(@Body LoginModel login);

    @POST("users/api/register")
    Call<LoginResponseModel> register(@Body RegisterModel registerModel);

    @POST("users/api/addTx")
    Call<NormalResponseModel> addTx(@Body TransactionModel transactionModel);
}
