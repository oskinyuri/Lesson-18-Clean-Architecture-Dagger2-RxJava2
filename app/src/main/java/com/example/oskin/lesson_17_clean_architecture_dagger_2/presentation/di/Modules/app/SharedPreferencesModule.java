package com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Modules.app;

import android.content.Context;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.data.sharedPreferences.settingsPreferences.SettingsDataSource;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.data.sharedPreferences.weatherPreferences.LastRequestManager;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.data.sharedPreferences.weatherPreferences.SelectedDayManager;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.data.sharedPreferences.weatherPreferences.SharedPrefManager;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Qualifier.ApplicationContext;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SharedPreferencesModule {

    @Provides
    @Singleton
    public SettingsDataSource provideUserSettingsManager(@ApplicationContext Context context){
        return new SettingsDataSource(context);
    }

    @Provides
    @Singleton
    public LastRequestManager provideLastRequestManager(@ApplicationContext Context context){
        return new LastRequestManager(context);
    }

    @Provides
    @Singleton
    public SelectedDayManager provideSelectedDayManager(@ApplicationContext Context context,
                                                        Gson gson){
        return new SelectedDayManager(context,gson);
    }

    @Provides
    @Singleton
    public SharedPrefManager provideSharedPrefManager(SettingsDataSource userSettingsManager,
                                                      LastRequestManager lastRequestManager,
                                                      SelectedDayManager selectedDayManager){
        return new SharedPrefManager(userSettingsManager,lastRequestManager,selectedDayManager);
    }

}
