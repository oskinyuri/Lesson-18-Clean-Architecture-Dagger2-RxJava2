package com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Components;

import android.content.Context;
import android.os.Handler;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.UserPreferencesInteractor;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.interfaces.dip.ISettingsRepository;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.interfaces.dip.IWeatherRepository;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Modules.app.ActivitiesModule;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Modules.app.ContextModule;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Modules.app.DatabaseModule;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Modules.app.GsonModule;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Modules.app.RepositoriesModule;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Modules.app.RetrofitHelperModule;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Modules.app.SharedPreferencesModule;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Qualifier.ApplicationContext;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Qualifier.SingleThread;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.WeatherApp;

import java.util.concurrent.ExecutorService;

import javax.inject.Singleton;

import dagger.Component;


@Component(modules = {
        ContextModule.class,
        RepositoriesModule.class,
        SharedPreferencesModule.class,
        RetrofitHelperModule.class,
        GsonModule.class,
        ActivitiesModule.class,
        DatabaseModule.class})

@Singleton
public interface AppComponent {

    @ApplicationContext
    Context context();

    @SingleThread
    ExecutorService executorService();

    ISettingsRepository iSettingRepository();

    IWeatherRepository iWeatherRepository();

    Handler handler();

    UserPreferencesInteractor getUserPreferencesInteractor();

    void injectApp(WeatherApp weatherApp);
}
