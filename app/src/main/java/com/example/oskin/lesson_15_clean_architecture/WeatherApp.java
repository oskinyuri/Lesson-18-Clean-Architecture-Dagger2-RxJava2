package com.example.oskin.lesson_15_clean_architecture;

import android.app.Application;

import com.example.oskin.lesson_15_clean_architecture.Presentation.DI.Components.AppComponent;
import com.example.oskin.lesson_15_clean_architecture.Presentation.DI.Components.DaggerAppComponent;
import com.example.oskin.lesson_15_clean_architecture.Presentation.DI.Modules.app.ContextModule;

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
