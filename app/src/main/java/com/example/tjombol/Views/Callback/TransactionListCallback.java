package com.example.tjombol.Views.Callback;

import com.example.tjombol.db.TxEntity;

public interface TransactionListCallback {
    void onTransactionClicked(TxEntity txEntity);
}
