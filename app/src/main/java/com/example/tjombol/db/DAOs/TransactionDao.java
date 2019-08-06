package com.example.tjombol.db.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tjombol.db.TxEntity;

import java.util.List;

@Dao
public interface TransactionDao {

    @Insert
    void insert(TxEntity txEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveTransactions(List<TxEntity> txEntities);

    @Update
    void update(TxEntity txEntity);

    @Delete
    void delete(TxEntity txEntity);

    @Query("DELETE FROM tx_table")
    void deleteAlltxs();

    @Query("SELECT * FROM tx_table ORDER BY date ")
    LiveData<List<TxEntity>> getAllTxs();

    @Query("SELECT * FROM tx_table ORDER BY date ")
    List<TxEntity> getAllTxsNormal();
}
