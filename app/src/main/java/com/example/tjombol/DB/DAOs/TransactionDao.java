package com.example.tjombol.DB.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tjombol.DB.Entities.NoteEntity;
import com.example.tjombol.DB.Entities.TxEntity;

import java.util.List;

@Dao
public interface TransactionDao {

    @Insert
    void insert(TxEntity txEntity);

    @Insert
    void saveTransactions(List<TxEntity> txEntities);

    @Update
    void update(TxEntity txEntity);

    @Delete
    void delete(TxEntity txEntity);

    @Query("DELETE FROM tx_table")
    void deleteAlltxs();

    @Query("SELECT * FROM tx_table ORDER BY priority ")
    LiveData<List<TxEntity>> getAllTxs();
}
