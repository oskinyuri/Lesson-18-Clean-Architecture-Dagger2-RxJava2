package com.example.oskin.lesson_15_clean_architecture.Presentation.DI.Components;

import com.example.oskin.lesson_15_clean_architecture.Presentation.DI.Modules.activity.WeekActivityModule;
import com.example.oskin.lesson_15_clean_architecture.Presentation.DI.Modules.scopes.WeekActivityScope;
import com.example.oskin.lesson_15_clean_architecture.Presentation.UI.MainWeekActivity;

import dagger.Component;

@Component(dependencies = AppComponent.class, modules = {WeekActivityModule.class})
@WeekActivityScope
public interface WeekActivityComponent {

    void injectActivity(MainWeekActivity mainWeekActivity);

}
