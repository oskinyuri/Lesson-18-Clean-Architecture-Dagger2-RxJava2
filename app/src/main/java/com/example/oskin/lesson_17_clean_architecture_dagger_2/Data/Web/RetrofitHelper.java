package com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.Web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;


import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    private static final String VERSION_API = "v1/";
    private static final String BASE_URL = "https://api.apixu.com/" + VERSION_API;

    public static final String KEY = "66d755c1072e47dcab4161954190202";

    private Retrofit mRetrofit;

    @Inject
    public RetrofitHelper(Retrofit retrofit){
        mRetrofit = retrofit;
    }

    public WeatherWebService getService(){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return mRetrofit.create(WeatherWebService.class);
    }

}
