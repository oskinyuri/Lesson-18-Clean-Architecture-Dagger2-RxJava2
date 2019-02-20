package com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.DI.Modules.activity;

import android.os.Handler;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.GetUserPreferencesInteractor;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.DIP.ISettingsRepository;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.SetUserPreferencesInteractor;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.DI.Modules.scopes.SettingActivityScope;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.DI.Qualifier.SingleThread;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.Presenters.SettingPresenter;

import java.util.concurrent.ExecutorService;

import dagger.Module;
import dagger.Provides;

@Module
public class SettingActivityModule {

    @SettingActivityScope
    @Provides
    public SettingPresenter provideSettingPresenter(
            SetUserPreferencesInteractor setUserPreferencesInteractor,
            GetUserPreferencesInteractor getUserPreferencesInteractor,
            @SingleThread ExecutorService executorService,
            Handler handler) {

        return new SettingPresenter(setUserPreferencesInteractor,
                getUserPreferencesInteractor,
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
