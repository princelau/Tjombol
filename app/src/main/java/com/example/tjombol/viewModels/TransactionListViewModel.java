package com.example.tjombol.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tjombol.db.TxEntity;
import com.example.tjombol.remote.Resource;
import com.example.tjombol.remote.Repositories.TransactionRepository;

import java.util.List;

import javax.inject.Inject;

public class TransactionListViewModel extends ViewModel {

    // Create a LiveData with a String
    private final LiveData<Resource<List<TxEntity>>> transactionsObservable;
    //TransactionRepository transactionRepository;
    // Rest of the ViewModel...
    @Inject
    TransactionListViewModel(TransactionRepository transactionRepository) {
        transactionsObservable = transactionRepository.loadTransactions();
    }

    public LiveData<Resource<List<TxEntity>>> getTransactionsObservable() {
        return transactionsObservable;
    }
}
