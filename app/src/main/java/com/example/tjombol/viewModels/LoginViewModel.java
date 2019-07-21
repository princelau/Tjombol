package com.example.tjombol.viewModels;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.example.tjombol.remote.ApiService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.tjombol.remote.ApiConstants.API_BASE_URL;

public class LoginViewModel extends Observable {

    private static final String TAG = "LoginViewModel";
    private Context context;
    public ObservableInt progressBar;
    public final ObservableField<String> email = new ObservableField<>("");
    public final ObservableField<String> password = new ObservableField<>("");


    private static Retrofit getRetrofitInstance() {
        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder().baseUrl(API_BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
    }

    public LoginViewModel(Context context) {
        this.context = context;
        progressBar = new ObservableInt(View.GONE);
    }

    public void sendLoginRequest(String email, String password) {
        progressBar.set(View.VISIBLE);

        ApiService apiService = getRetrofitInstance().create(ApiService.class);
        Call<String> loginResponse = apiService.login(email, password);
        loginResponse.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                progressBar.set(View.GONE);
                Log.d(TAG, "onResponse: "+response);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                progressBar.set(View.GONE);
                showToast("Time out, failed to connect to remote!");
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    void showToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
