package com.ramesh.android.components1.view.base;

import androidx.lifecycle.ViewModel;

import com.ramesh.android.components1.model.data.repositrory.Repository;
import com.ramesh.android.components1.utils.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel {

    private Repository repository;
    private SchedulerProvider schedulerProvider;
    private CompositeDisposable compositeDisposable;

    public BaseViewModel(Repository repository, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        this.repository = repository;
        this.schedulerProvider = schedulerProvider;
        this.compositeDisposable = compositeDisposable;
    }

    public Repository getRepository() {
        return repository;
    }

    public SchedulerProvider getSchedulerProvider() {
        return schedulerProvider;
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }
}
