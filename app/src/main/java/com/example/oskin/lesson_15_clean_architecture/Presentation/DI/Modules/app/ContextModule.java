package com.example.oskin.lesson_15_clean_architecture.Presentation.DI.Modules.app;

import android.content.Context;

import com.example.oskin.lesson_15_clean_architecture.Presentation.DI.Qualifier.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    Context context;

    public ContextModule(Context context){
        this.context = context;
    }

    @Provides
    @Singleton
    @ApplicationContext
    public Context context(){
        return context.getApplicationContext();
    }

}
