package com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Modules.activity;

import android.os.Handler;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.UserPreferencesInteractor;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.interfaces.dip.ISettingsRepository;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.SetUserPreferencesInteractor;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Modules.scopes.SettingActivityScope;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Qualifier.SingleThread;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.presenters.SettingPresenter;

import java.util.concurrent.ExecutorService;

import dagger.Module;
import dagger.Provides;

@Module
public class SettingActivityModule {

    @SettingActivityScope
    @Provides
    public SettingPresenter provideSettingPresenter(
            SetUserPreferencesInteractor setUserPreferencesInteractor,
            UserPreferencesInteractor userPreferencesInteractor,
            @SingleThread ExecutorService executorService,
            Handler handler) {

        return new SettingPresenter(setUserPreferencesInteractor,
                userPreferencesInteractor,
                executorService,
                handler);
    }

    @SettingActivityScope
    @Provides
    public SetUserPreferencesInteractor provideSetUserPreferencesInteractor(
            ISettingsRepository iSettingsRepository) {

        return new SetUserPreferencesInteractor(iSettingsRepository);
    }

}
