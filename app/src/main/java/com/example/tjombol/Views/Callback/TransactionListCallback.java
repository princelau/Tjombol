package com.example.tjombol.Views.Callback;

import com.example.tjombol.DB.Entities.TxEntity;

public interface TransactionListCallback {
    void onTransactionClicked(TxEntity txEntity);
}
