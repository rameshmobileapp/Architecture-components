
package com.ramesh.android.components1.di.component;


import com.ramesh.android.components1.di.PerActivity;
import com.ramesh.android.components1.di.module.ActivityModule;
import com.ramesh.android.components1.view.activity.notes.NotesActivity;

import dagger.Component;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(NotesActivity activity);

}
