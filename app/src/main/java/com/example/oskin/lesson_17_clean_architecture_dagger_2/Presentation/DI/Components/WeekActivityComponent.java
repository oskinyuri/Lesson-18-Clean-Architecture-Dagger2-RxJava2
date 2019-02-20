package com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.DI.Components;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.DI.Modules.activity.WeekActivityModule;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.DI.Modules.scopes.WeekActivityScope;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.UI.MainWeekActivity;

import dagger.Component;

@Component(dependencies = AppComponent.class, modules = {WeekActivityModule.class})
@WeekActivityScope
public interface WeekActivityComponent {

    void injectActivity(MainWeekActivity mainWeekActivity);

}
