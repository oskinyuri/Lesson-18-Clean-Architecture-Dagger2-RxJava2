package com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Modules.app;


import android.os.Handler;
import android.os.Looper;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.UserPreferencesInteractor;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.interfaces.dip.ISettingsRepository;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Qualifier.SingleThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivitiesModule {

    @Provides
    @Singleton
    public Handler provideHandler(){
        return new Handler(Looper.getMainLooper());
    }

    @Provides
    @SingleThread
    @Singleton
    public ExecutorService provideExecutorService(){
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    @Singleton
    public UserPreferencesInteractor provideGetUserPreferencesInteractor(ISettingsRepository iSettingsRepository){
        return new UserPreferencesInteractor(iSettingsRepository);
    }

}
