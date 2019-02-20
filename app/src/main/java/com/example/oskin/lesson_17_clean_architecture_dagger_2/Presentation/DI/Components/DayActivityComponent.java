package com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.DI.Components;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.DI.Modules.activity.DayActivityModule;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.DI.Modules.scopes.DayActivityScope;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.UI.DayActivity;

import dagger.Component;

@Component(dependencies = AppComponent.class, modules = DayActivityModule.class)
@DayActivityScope
public interface DayActivityComponent {

    void injectActivity(DayActivity dayActivity);

}
