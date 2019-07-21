package com.example.tjombol.remote;

import com.example.tjombol.db.TxEntity;
import com.example.tjombol.remote.Models.TransactionResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    // get
    @GET("api/getTx")
    Call<List<TxEntity>> getTransactions();

    // post
    @FormUrlEncoded
    @POST("api/login")
    Call<String> login(@Field("email") String email, @Field("password") String password);
}
