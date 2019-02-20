package com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.DI.Modules.app;

import android.content.Context;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.DI.Qualifier.ApplicationContext;

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
