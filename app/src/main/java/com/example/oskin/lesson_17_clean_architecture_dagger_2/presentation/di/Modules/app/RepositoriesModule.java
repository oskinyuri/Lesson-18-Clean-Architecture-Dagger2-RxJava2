package com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Modules.app;

import android.content.Context;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.data.db.LocalDataSource;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.data.repositories.settingsRepository.SettingRepository;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.data.repositories.weatherRepository.WeatherMapper;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.data.repositories.weatherRepository.WeatherRepository;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.data.sharedPreferences.settingsPreferences.SettingsDataSource;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.data.sharedPreferences.weatherPreferences.SharedPrefManager;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.data.web.RemoteDataSource;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.interfaces.dip.ISettingsRepository;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.interfaces.dip.IWeatherRepository;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Qualifier.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoriesModule {

    @Provides
    @Singleton
    public ISettingsRepository provideSettingRepository(
            @ApplicationContext Context context,
            SettingsDataSource userSettingsManager) {
        return new SettingRepository(context, userSettingsManager);
    }

    @Provides
    @Singleton
    public IWeatherRepository provideWeatherRepository(
            @ApplicationContext Context context,
            RemoteDataSource remoteDataSource,
            LocalDataSource localDataSource,
            SharedPrefManager sharedPrefManager,
            WeatherMapper weatherMapper) {

        return new WeatherRepository(
                context,
                remoteDataSource,
                localDataSource,
                sharedPrefManager,
                weatherMapper);
    }

    @Provides
    @Singleton
    public WeatherMapper provideWeatherMapper(){
        return new WeatherMapper();
    }

}
