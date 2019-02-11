package com.example.oskin.lesson_15_clean_architecture;

import android.app.Application;
import com.example.oskin.lesson_15_clean_architecture.Data.Repositories.Repository;
import com.example.oskin.lesson_15_clean_architecture.Data.Web.RetrofitHelper;

public class WeatherApp extends Application {

    private static Repository mRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        mRepository = new Repository(getApplicationContext());
    }

    public static Repository getRepository(){
        return mRepository;
    }
}
