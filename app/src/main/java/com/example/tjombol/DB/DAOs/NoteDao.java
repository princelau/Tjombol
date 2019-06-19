package com.example.tjombol.DB.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tjombol.DB.Entities.NoteEntity;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void insert(NoteEntity noteEntity);

    @Update
    void update(NoteEntity noteEntity);

    @Delete
    void delete(NoteEntity noteEntity);

    @Query("DELETE FROM note_table")
    void deleteAllnotes();

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    LiveData<List<NoteEntity>> getAllNotes();
}
