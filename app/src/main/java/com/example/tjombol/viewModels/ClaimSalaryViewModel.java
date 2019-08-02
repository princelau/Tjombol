package com.example.tjombol.viewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tjombol.remote.ApiService;
import com.example.tjombol.remote.Models.NormalResponseModel;
import com.example.tjombol.remote.Models.TransactionModel;
import com.example.tjombol.remote.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClaimSalaryViewModel extends ViewModel{
    private static final String TAG = "ClaimSalaryViewModel";
    private NormalResponseModel normalResponseModel;
    private MutableLiveData<NormalResponseModel> normalResponseModelMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<String> liveMsg = new MutableLiveData<>();

    public MutableLiveData<String> getLiveMsg() {
        if(liveMsg == null){
            liveMsg = new MutableLiveData<>();
            liveMsg.setValue("n");
        }
        return liveMsg;
    }

    public void getTransactionFeedBack(String rAccount,String amount,String pPurpose ) {
        if (rAccount != null || amount != null || pPurpose != null) {
            ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
            TransactionModel transactionModel = new TransactionModel(rAccount, amount, pPurpose);
            Call<NormalResponseModel> normalResponseModelCall = apiService.addTx(transactionModel);
            normalResponseModelCall.enqueue(new Callback<NormalResponseModel>() {
                @Override
                public void onResponse(Call<NormalResponseModel> call, Response<NormalResponseModel> response) {
                    //progressBar.set(View.GONE);
                    if (response.body() != null && response.body().getMsg() != null) {
                        liveMsg.setValue(response.body().getMsg());
                    } else {
                        Log.d(TAG, "onResponse: shit happens");
                    }
                }

                @Override
                public void onFailure(Call<NormalResponseModel> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t.getMessage());
                }
            });
        } else {
            liveMsg.setValue("TimeOut");
        }
    }

}
