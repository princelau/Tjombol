package com.example.tjombol.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.tjombol.db.DAOs.TransactionDao;

@Database(entities = {TxEntity.class},version = 3)
public abstract class TxDatabase extends RoomDatabase {

    public abstract TransactionDao transactionDao();
}

