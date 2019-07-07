package com.ramesh.android.components1.model.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.ramesh.android.components1.model.data.local.model.Notes;

import java.util.List;

import io.reactivex.Observable;


@Dao
public interface AppDatabaseDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertNotes(Notes notes);

    @Update()
    void updateNotes(Notes notes);

    @Delete()
    void deleteNotes(Notes notes);

    @Query("SELECT * FROM notes order by id")
    LiveData<List<Notes>> getAllNotes();

    @Query("Delete from notes")
    void deleteAllNote();

}
