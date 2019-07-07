package com.ramesh.android.components1;

import android.app.Application;

import com.ramesh.android.components1.di.component.ApplicationComponent;
import com.ramesh.android.components1.di.component.DaggerApplicationComponent;
import com.ramesh.android.components1.di.module.ApplicationModule;

public class App extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        applicationComponent.inject(this);
    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }

    public void setComponent(ApplicationComponent applicationComponent) {
        this.applicationComponent = applicationComponent;
    }
}
