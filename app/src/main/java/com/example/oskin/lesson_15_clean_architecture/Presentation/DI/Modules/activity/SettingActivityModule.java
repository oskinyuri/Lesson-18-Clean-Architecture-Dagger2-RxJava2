package com.example.oskin.lesson_15_clean_architecture.Presentation.DI.Modules.activity;

import android.os.Handler;

import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.GetUserPreferencesInteractor;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.DIP.ISettingsRepository;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.SetUserPreferencesInteractor;
import com.example.oskin.lesson_15_clean_architecture.Presentation.DI.Modules.scopes.SettingActivityScope;
import com.example.oskin.lesson_15_clean_architecture.Presentation.DI.Qualifier.SingleThread;
import com.example.oskin.lesson_15_clean_architecture.Presentation.Presenters.SettingPresenter;

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
