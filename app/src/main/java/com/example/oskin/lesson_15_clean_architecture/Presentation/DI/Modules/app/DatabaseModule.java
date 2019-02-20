package com.example.oskin.lesson_15_clean_architecture.Presentation.DI.Modules.app;

import android.content.Context;

import com.example.oskin.lesson_15_clean_architecture.Data.DB.DatabaseManager;
import com.example.oskin.lesson_15_clean_architecture.Presentation.DI.Qualifier.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Provides
    @Singleton
    DatabaseManager provideDatabaseManager(@ApplicationContext Context context){
        return new DatabaseManager(context);
    }

}
