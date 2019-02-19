package com.example.oskin.lesson_15_clean_architecture.Presentation.DI;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    Context context;

    public ContextModule(Context context){
        this.context = context;
    }

    @Provides
    public Context context(){
        return context.getApplicationContext();
    }

}
