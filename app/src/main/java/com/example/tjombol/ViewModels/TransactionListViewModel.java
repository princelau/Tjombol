package com.example.tjombol.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tjombol.DB.Entities.TxEntity;
import com.example.tjombol.Remote.Repositories.Service.Resource;
import com.example.tjombol.Remote.Repositories.TransactionRepository;

import java.util.List;

import javax.inject.Inject;

public class TransactionListViewModel extends ViewModel {

    // Create a LiveData with a String
    private final LiveData<Resource<List<TxEntity>>> transactionsObservable;
    TransactionRepository transactionRepository;
    // Rest of the ViewModel...
    @Inject
    public TransactionListViewModel(TransactionRepository transactionRepository) {
        transactionsObservable = transactionRepository.loadTransactions();
    }

    public LiveData<Resource<List<TxEntity>>> getTransactionsObservable() {
        return transactionsObservable;
    }
}
