package com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.DI.Components;

import android.content.Context;
import android.os.Handler;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.GetUserPreferencesInteractor;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.DIP.ISettingsRepository;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.DIP.IWeatherRepository;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.DI.Modules.app.ActivitiesModule;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.DI.Modules.app.ContextModule;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.DI.Modules.app.DatabaseModule;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.DI.Modules.app.GsonModule;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.DI.Modules.app.RepositoriesModule;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.DI.Modules.app.RetrofitHelperModule;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.DI.Modules.app.SharedPreferencesModule;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.DI.Qualifier.ApplicationContext;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.DI.Qualifier.SingleThread;
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

    GetUserPreferencesInteractor getUserPreferencesInteractor();

    void injectApp(WeatherApp weatherApp);
}
