package com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Components;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Modules.activity.WeekActivityModule;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Modules.scopes.WeekActivityScope;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.ui.MainWeekActivity;

import dagger.Component;

@Component(dependencies = AppComponent.class, modules = {WeekActivityModule.class})
@WeekActivityScope
public interface WeekActivityComponent {

    void injectActivity(MainWeekActivity mainWeekActivity);

}
