package com.example.oskin.lesson_15_clean_architecture.Presentation.DI.Components;

import com.example.oskin.lesson_15_clean_architecture.Presentation.DI.Modules.activity.DayActivityModule;
import com.example.oskin.lesson_15_clean_architecture.Presentation.DI.Modules.scopes.DayActivityScope;
import com.example.oskin.lesson_15_clean_architecture.Presentation.UI.DayActivity;

import dagger.Component;

@Component(dependencies = AppComponent.class, modules = DayActivityModule.class)
@DayActivityScope
public interface DayActivityComponent {

    void injectActivity(DayActivity dayActivity);

}
