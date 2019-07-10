package com.example.tjombol.remote;

import com.example.tjombol.db.TxEntity;
import com.example.tjombol.remote.Models.TransactionResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("api/getTx")
    Call<List<TxEntity>> getTransactions();
}
