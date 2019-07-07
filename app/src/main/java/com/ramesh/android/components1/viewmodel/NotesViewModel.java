package com.ramesh.android.components1.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ramesh.android.components1.model.data.local.AppDatabase;
import com.ramesh.android.components1.model.data.local.model.Notes;
import com.ramesh.android.components1.model.data.repositrory.Repository;
import com.ramesh.android.components1.utils.SchedulerProvider;
import com.ramesh.android.components1.view.base.BaseViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class NotesViewModel extends BaseViewModel {

    private static final String TAG = "NotesViewModel";
    LiveData<List<Notes>> notesLiveData;


    NotesViewModel(Repository repository, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(repository, schedulerProvider, compositeDisposable);
        notesLiveData = getRepository().getAllNotes();
    }

    public void insertNotes(Notes notes) {
        getCompositeDisposable().add(
                getRepository()
                        .insertNotes(notes)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(rowID -> {
                            Log.d(TAG, "insertNotes: success" + rowID);
                        }, throwable -> {
                            Log.d(TAG, "insertNotes: throwable");
                        }));
    }

    public LiveData<List<Notes>> getNotesLiveData() {
        return notesLiveData;
    }

    public void deleteNotes(Notes notes) {
        getCompositeDisposable().add(
                getRepository()
                        .deleteNotes(notes)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(actionFlag -> {
                            Log.d(TAG, "deleteNotes: success" + actionFlag);
                        }, throwable -> {
                            Log.d(TAG, "deleteNotes: throwable");
                        }));
    }

    public void updateNotes(Notes notes) {
        getCompositeDisposable().add(
                getRepository()
                        .updateNotes(notes)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(actionFlag -> {
                            Log.d(TAG, "updateNotes: success" + actionFlag);
                        }, throwable -> {
                            Log.d(TAG, "updateNotes: throwable");
                        }));
    }
}
