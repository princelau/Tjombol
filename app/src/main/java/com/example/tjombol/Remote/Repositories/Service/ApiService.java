package com.example.tjombol.Remote.Repositories.Service;

import com.example.tjombol.DB.Entities.TxEntity;
import com.example.tjombol.Remote.Models.TransactionResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("api/getTx")
    Call<TransactionResponse> getTransactions();
}
