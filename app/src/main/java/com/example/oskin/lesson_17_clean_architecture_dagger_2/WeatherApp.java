package com.example.oskin.lesson_17_clean_architecture_dagger_2;

import android.app.Application;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Components.AppComponent;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Components.DaggerAppComponent;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Modules.app.ContextModule;

public class WeatherApp extends Application {

    private static WeatherApp sWeatherApp;

    public static WeatherApp getInstance(){
        return sWeatherApp;
    }

    private AppComponent mAppComponent;


    @Override
    public void onCreate() {
        super.onCreate();


        sWeatherApp = WeatherApp.this;

        mAppComponent = DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }
}
