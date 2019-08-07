package com.example.tjombol.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Transaction;

import com.example.tjombol.db.TxEntity;
import com.example.tjombol.remote.Resource;
import com.example.tjombol.remote.Repositories.TransactionRepository;

import java.util.List;

import javax.inject.Inject;

public class TransactionListViewModel extends ViewModel {

    // Create a LiveData with a String
    private MutableLiveData<Resource<List<TxEntity>>> transactionsObservable = new MutableLiveData<>();
    //TransactionRepository transactionRepository;
    // Rest of the ViewModel...
    @Inject
    TransactionListViewModel(TransactionRepository transactionRepository) {
        transactionsObservable = (MutableLiveData<Resource<List<TxEntity>>>) transactionRepository.loadTransactions();
    }

    public void setTransactionsObservable(Resource<List<TxEntity>> resource){
        transactionsObservable.setValue(resource);
    }

    public LiveData<Resource<List<TxEntity>>> getTransactionsObservable() {
        return transactionsObservable;
    }
}
