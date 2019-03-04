package com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Modules.app;

import android.content.Context;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.data.db.LocalDataSource;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Qualifier.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Provides
    @Singleton
    LocalDataSource provideDatabaseManager(@ApplicationContext Context context){
        return new LocalDataSource(context);
    }

}
