package com.example.oskin.lesson_15_clean_architecture;

import android.app.Application;
import com.example.oskin.lesson_15_clean_architecture.Data.Repositories.Repository;
import com.example.oskin.lesson_15_clean_architecture.Data.Web.RetrofitHelper;

public class WeatherApp extends Application {

    private static Repository mRepository;

    private static RetrofitHelper mRetrofitHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        mRetrofitHelper = new RetrofitHelper();
        mRepository = new Repository(getApplicationContext());
        //initRepository();
    }

    /*private void initRepository(){
        Thread initRepo = new Thread(new Runnable() {
            @Override
            public void run() {
                mRepository = new Repository(getApplicationContext());
            }
        });
        initRepo.start();
    }*/

    public static Repository getRepository(){
        return mRepository;
    }
}
