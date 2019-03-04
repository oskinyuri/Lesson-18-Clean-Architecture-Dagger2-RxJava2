package com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Components;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.SelectedDayInteractor;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.UserPreferencesInteractor;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Modules.activity.WeekActivityModule;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Modules.scopes.WeekActivityScope;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.ui.WeekActivity;

import dagger.Component;

@Component(dependencies = AppComponent.class, modules = {WeekActivityModule.class})
@WeekActivityScope
public interface WeekActivityComponent {

    UserPreferencesInteractor getUserPreferencesInteractor();

    SelectedDayInteractor getSelectedDayInteractor();

    void injectActivity(WeekActivity weekActivity);

}
