package com.example.tjombol.Service;

import com.example.tjombol.Models.TransactionModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("api/getTx")
    Call<List<TransactionModel>> getTransactions();
}
