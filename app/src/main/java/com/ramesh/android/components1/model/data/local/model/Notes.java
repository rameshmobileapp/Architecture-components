package com.ramesh.android.components1.model.data.local.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Notes {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int id;


    @ColumnInfo(name = "tittle")
    private String tittle;

    @ColumnInfo(name = "notes")
    private String notes;

    public Notes(String tittle, String notes) {
        this.tittle = tittle;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
