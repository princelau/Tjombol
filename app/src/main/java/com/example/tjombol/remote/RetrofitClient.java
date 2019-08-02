package com.example.tjombol.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.tjombol.remote.ApiConstants.API_BASE_URL;

public class RetrofitClient {

    private static Retrofit retrofit;
    private static final String BASE_URL = API_BASE_URL;
    // Singleton Pattern
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}