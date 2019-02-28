package com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.DI.Modules.app;

import android.content.Context;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.DB.DatabaseManager;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.Repositories.SettingsRepository.SettingRepository;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.Repositories.WeatherRepository.WeatherMapper;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.Repositories.WeatherRepository.WeatherRepository;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.SharedPreferences.SettingsPreferences.UserSettingsManager;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.SharedPreferences.WeatherPreferences.SharedPrefManager;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.Web.RemoteDataSource;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.DIP.ISettingsRepository;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.DIP.IWeatherRepository;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.DI.Qualifier.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoriesModule {

    @Provides
    @Singleton
    public ISettingsRepository provideSettingRepository(
            @ApplicationContext Context context,
            UserSettingsManager userSettingsManager) {
        return new SettingRepository(context, userSettingsManager);
    }

    @Provides
    @Singleton
    public IWeatherRepository provideWeatherRepository(
            @ApplicationContext Context context,
            RemoteDataSource remoteDataSource,
            DatabaseManager databaseManager,
            SharedPrefManager sharedPrefManager,
            WeatherMapper weatherMapper) {

        return new WeatherRepository(
                context,
                remoteDataSource,
                databaseManager,
                sharedPrefManager,
                weatherMapper);
    }

    @Provides
    @Singleton
    public WeatherMapper provideWeatherMapper(){
        return new WeatherMapper();
    }

}
