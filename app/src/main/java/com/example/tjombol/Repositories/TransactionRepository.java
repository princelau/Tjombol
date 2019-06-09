package com.example.tjombol.Repositories;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tjombol.Models.TransactionModel;
import com.example.tjombol.Service.APIService;
import com.example.tjombol.Service.APIUrl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TransactionRepository {

    Application application;
    private static OkHttpClient providesOkHttpClientBuilder(){

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        return httpClient.readTimeout(1200, TimeUnit.SECONDS)
                .connectTimeout(1200, TimeUnit.SECONDS).build();

    }


    //private List<TransactionModel> webserviceResponseList = new ArrayList<>();
    public LiveData<List<TransactionModel>> providesWebService() {
        final MutableLiveData<List<TransactionModel>> data = new MutableLiveData<>();

        //String response = "";
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(APIUrl.API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(providesOkHttpClientBuilder())
                    .build();

            //Defining retrofit api service
            APIService apiService = retrofit.create(APIService.class);
            //response = service.makeRequest().execute().body();

            Call<List<TransactionModel>> call = apiService.getTransactions();

            call.enqueue(new Callback<List<TransactionModel>>(){
                @Override
                public void onResponse(Call<List<TransactionModel>> call, Response<List<TransactionModel>> response) {
                    if(!response.isSuccessful()){
                        Log.d("Tx Repository","Code: "+ response.code());
                        //textView.setText("Code: "+ response.code());
                        return;
                    }

                    Log.d("Tx Repository","Response::::"+response.body());
                    List<TransactionModel> transactions = response.body();

                    /*
                    for(TransactionModel transaction:transactions){
                        String content = "";
                        content += "txId: "+transaction.getTransactionId()+"\n";
                        content += "comment: "+transaction.getComment()+"\n";
                        content += "date: "+transaction.getDate()+"\n";
                        content += "sender: "+transaction.getSender()+"\n";
                        content += "receiver: "+transaction.getReceiver()+"\n";
                        content += "txStatus: "+transaction.getTransactionStatus()+"\n";
                        content += "type: "+transaction.getType()+"\n";
                        content += "amount: "+transaction.getAmount()+"\n";
                        Log.d("Tx Repository","Response::::"+response.body());
                    }
                    */
                    data.setValue(transactions);
                }

                @Override
                public void onFailure(Call<List<TransactionModel>> call, Throwable t) {
                    Log.d("Repository","Failed:::");
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }

        //  return retrofit.create(ResultModel.class);
        return  data;

    }
}
