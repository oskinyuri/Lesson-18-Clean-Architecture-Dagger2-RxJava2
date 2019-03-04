package com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Modules.activity;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.UserPreferencesInteractor;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Modules.scopes.SettingActivityScope;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.presenters.SettingPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class SettingActivityModule {

    @SettingActivityScope
    @Provides
    public SettingPresenter provideSettingPresenter(UserPreferencesInteractor userPreferencesInteractor) {

        return new SettingPresenter(userPreferencesInteractor);
    }
/*
    @SettingActivityScope
    @Provides
    public SetUserPreferencesInteractor provideSetUserPreferencesInteractor(
            ISettingsRepository iSettingsRepository) {

        return new SetUserPreferencesInteractor(iSettingsRepository);
    }*/

}
