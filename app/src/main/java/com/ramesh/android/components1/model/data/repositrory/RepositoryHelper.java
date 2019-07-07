package com.ramesh.android.components1.model.data.repositrory;

import androidx.lifecycle.LiveData;

import com.ramesh.android.components1.model.data.local.model.Notes;

import java.util.List;

import io.reactivex.Observable;

public interface RepositoryHelper {


    LiveData<List<Notes>> getAllNotes();

    Observable<Long> insertNotes(Notes notes);

    Observable<Boolean> deleteNotes(Notes notes);

    Observable<Boolean> updateNotes(Notes notes);
}
