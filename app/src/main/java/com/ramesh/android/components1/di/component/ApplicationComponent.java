
package com.ramesh.android.components1.di.component;

import android.app.Application;
import android.content.Context;

import com.ramesh.android.components1.App;
import com.ramesh.android.components1.di.ApplicationContext;
import com.ramesh.android.components1.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(App app);

    @ApplicationContext
    Context context();

    Application application();



}