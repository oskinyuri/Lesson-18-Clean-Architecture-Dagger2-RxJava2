package com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Components;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Modules.activity.DayActivityModule;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Modules.scopes.DayActivityScope;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.ui.DayActivity;

import dagger.Component;

@Component(dependencies = {WeekActivityComponent.class}, modules = {DayActivityModule.class})
@DayActivityScope
public interface DayActivityComponent {

    void injectActivity(DayActivity dayActivity);

}
