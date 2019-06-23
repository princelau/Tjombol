package com.example.tjombol.remote;

import com.example.tjombol.remote.Models.TransactionResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("api/getTx")
    Call<TransactionResponse> getTransactions();
}
