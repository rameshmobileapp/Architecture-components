package com.ramesh.android.components1.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


import com.ramesh.android.components1.di.PerActivity;
import com.ramesh.android.components1.model.data.repositrory.Repository;
import com.ramesh.android.components1.utils.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;


public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

    private final Repository repository;
    private final SchedulerProvider schedulerProvider;
    private final CompositeDisposable compositeDisposable;


    @Inject
    public ViewModelProviderFactory(Repository repository,SchedulerProvider schedulerProvider,CompositeDisposable compositeDisposable) {
        this.repository = repository;
        this.schedulerProvider = schedulerProvider;
        this.compositeDisposable = compositeDisposable;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(NotesViewModel.class)) {
            return (T) new NotesViewModel(repository,schedulerProvider,compositeDisposable);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
        }
    }
}