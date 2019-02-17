package com.example.oskin.lesson_15_clean_architecture;

import android.app.Application;

import com.example.oskin.lesson_15_clean_architecture.Data.Repositories.SettingsRepository.SettingRepository;
import com.example.oskin.lesson_15_clean_architecture.Data.Repositories.WeatherRepository.WeatherRepository;

public class WeatherApp extends Application {

    private static WeatherRepository mWeatherRepository;

    private static SettingRepository mSettingRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        mWeatherRepository = new WeatherRepository(getApplicationContext());
        mSettingRepository = new SettingRepository(getApplicationContext());

    }

    public static WeatherRepository getWeatherRepository(){
        return mWeatherRepository;
    }

    public static SettingRepository getSettingRepository() {
        return mSettingRepository;
    }
}
