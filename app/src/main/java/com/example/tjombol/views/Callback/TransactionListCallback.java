package com.example.tjombol.views.Callback;

import com.example.tjombol.db.TxEntity;

public interface TransactionListCallback {
    void onTransactionClicked(TxEntity txEntity);
}
