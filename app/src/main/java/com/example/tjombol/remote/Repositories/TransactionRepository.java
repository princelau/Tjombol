package com.example.tjombol.remote.Repositories;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tjombol.db.DAOs.TransactionDao;
import com.example.tjombol.db.TxEntity;
import com.example.tjombol.remote.ApiService;
import com.example.tjombol.remote.NetworkBoundResource;
import com.example.tjombol.remote.Resource;
import com.example.tjombol.remote.UserConstant;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class TransactionRepository {
    private static final String TAG = "TxRepository";
    private final TransactionDao transactionDao;
    private final ApiService apiService;
    //private final LoginResponseModel userModel;

    @Inject
    TransactionRepository(TransactionDao transactionDao, ApiService service, Application application) {
        this.transactionDao = transactionDao;
        this.apiService = service;
        //this.userModel = userModel;
    }
    /**
     * This method fetches the transactions from the service.
     * Once the fetching is done the data is cached to local db so that the app can even work offline
     * @return List of transactions
     * <resulttype,resourcetype>
     */
    public LiveData<Resource<List<TxEntity>>> loadTransactions() {
        // resourceType and resultType
        return new NetworkBoundResource<List<TxEntity>, List<TxEntity>>() {

            @Override
            protected void saveCallResult(List<TxEntity> item) {
                if(null != item)
                    Log.d(TAG, "saveCallResult: "+item);
                    transactionDao.saveTransactions(item);
            }

            @NonNull
            @Override
            protected LiveData<List<TxEntity>> loadAllTxFromDb() {
                return transactionDao.getAllTxs();
            }

            @NonNull
            @Override
            protected Call<List<TxEntity>> createCall() {
                //return apiService.getTransactions(userModel.getAccount());
                UserConstant userConstant= UserConstant.getInstance();
                Log.d(TAG, "createCall on user account: "+userConstant.getUser_account());
                return apiService.getTransactions(userConstant.getUser_account());
            }
        }.getAsLiveData();
    }
}