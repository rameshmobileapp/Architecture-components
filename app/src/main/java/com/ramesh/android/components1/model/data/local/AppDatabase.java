package com.ramesh.android.components1.model.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.ramesh.android.components1.model.data.local.model.Notes;


@Database(entities = Notes.class, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AppDatabaseDAO appDatabaseDAO();
}
