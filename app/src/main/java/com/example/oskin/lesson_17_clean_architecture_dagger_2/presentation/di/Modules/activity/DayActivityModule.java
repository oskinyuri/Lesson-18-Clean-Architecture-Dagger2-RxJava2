package com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Modules.activity;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.SelectedDayInteractor;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.UserPreferencesInteractor;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Modules.scopes.DayActivityScope;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.presenters.DayPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class DayActivityModule {

    @DayActivityScope
    @Provides
    public DayPresenter provideDayPresenter(SelectedDayInteractor selectedDayInteractor,
                                            UserPreferencesInteractor userPreferencesInteractor) {
        return new DayPresenter(selectedDayInteractor,
                userPreferencesInteractor);
    }
}
