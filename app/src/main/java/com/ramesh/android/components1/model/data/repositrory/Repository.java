package com.ramesh.android.components1.model.data.repositrory;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.ramesh.android.components1.di.ApplicationContext;
import com.ramesh.android.components1.di.DatabaseInfo;
import com.ramesh.android.components1.model.data.local.AppDatabase;
import com.ramesh.android.components1.model.data.local.model.Notes;
import com.ramesh.android.components1.utils.AppConstant;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;


public class Repository implements RepositoryHelper {

    private AppDatabase appDatabase;

    @Inject
    public Repository(@ApplicationContext Context context) {
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, AppConstant.DB_NAME)
                .build();
    }

    @Override
    public LiveData<List<Notes>> getAllNotes() {
        return appDatabase.appDatabaseDAO().getAllNotes();
    }

    @Override
    public Observable<Long> insertNotes(Notes notes) {
        return Observable.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return appDatabase.appDatabaseDAO().insertNotes(notes);
            }
        });
    }

    @Override
    public Observable<Boolean> deleteNotes(Notes notes) {
        return Observable.fromCallable(() -> {
            appDatabase.appDatabaseDAO().deleteNotes(notes);
            return true;
        });
    }

    @Override
    public Observable<Boolean> updateNotes(Notes notes) {
        return Observable.fromCallable(() -> {
            appDatabase.appDatabaseDAO().updateNotes(notes);
            return true;
        });
    }
}
