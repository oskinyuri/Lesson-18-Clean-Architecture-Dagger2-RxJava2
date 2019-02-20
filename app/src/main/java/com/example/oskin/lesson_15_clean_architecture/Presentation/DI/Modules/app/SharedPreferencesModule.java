package com.example.oskin.lesson_15_clean_architecture.Presentation.DI.Modules.app;

import android.content.Context;

import com.example.oskin.lesson_15_clean_architecture.Data.SharedPreferences.SettingsPreferences.UserSettingsManager;
import com.example.oskin.lesson_15_clean_architecture.Data.SharedPreferences.WeatherPreferences.LastRequestManager;
import com.example.oskin.lesson_15_clean_architecture.Data.SharedPreferences.WeatherPreferences.SelectedDayManager;
import com.example.oskin.lesson_15_clean_architecture.Data.SharedPreferences.WeatherPreferences.SharedPrefManager;
import com.example.oskin.lesson_15_clean_architecture.Presentation.DI.Qualifier.ApplicationContext;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SharedPreferencesModule {

    @Provides
    @Singleton
    public UserSettingsManager provideUserSettingsManager(@ApplicationContext Context context){
        return new UserSettingsManager(context);
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
    public SharedPrefManager provideSharedPrefManager(UserSettingsManager userSettingsManager,
                                                      LastRequestManager lastRequestManager,
                                                      SelectedDayManager selectedDayManager){
        return new SharedPrefManager(userSettingsManager,lastRequestManager,selectedDayManager);
    }

}
