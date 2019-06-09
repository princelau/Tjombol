package com.example.tjombol.ViewModels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.tjombol.Models.TransactionModel;
import com.example.tjombol.Repositories.TransactionRepository;

import java.util.List;

public class TransactionViewModel extends AndroidViewModel {

    // Create a LiveData with a String
    private final LiveData<List<TransactionModel>> retroObservable;
    TransactionRepository transactionRepository;
    // Rest of the ViewModel...
    public TransactionViewModel(Application application){
        super(application);
        transactionRepository = new TransactionRepository();
        retroObservable = transactionRepository.providesWebService();
    }


    public LiveData<List<TransactionModel>> getProjectRetroListObservable() {
        return retroObservable;
    }
}
