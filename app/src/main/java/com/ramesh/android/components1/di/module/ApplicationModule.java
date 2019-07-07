package com.ramesh.android.components1.di.module;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.ramesh.android.components1.R;
import com.ramesh.android.components1.di.ApiInfo;
import com.ramesh.android.components1.di.ApplicationContext;
import com.ramesh.android.components1.di.DatabaseInfo;
import com.ramesh.android.components1.model.data.local.AppDatabase;
import com.ramesh.android.components1.model.data.repositrory.Repository;
import com.ramesh.android.components1.model.data.repositrory.RepositoryHelper;
import com.ramesh.android.components1.utils.AppConstant;
import com.ramesh.android.components1.utils.AppSchedulerProvider;
import com.ramesh.android.components1.utils.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    // Context declaration
    @Provides
    @ApplicationContext
    Context provideContext() {
        return application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    //String declaration



    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstant.DB_NAME;
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/RobotoCondensed-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }

    @Provides
    @Singleton
    Repository provideRepository(Repository repository) {
        return repository;
    }



}
