package com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Modules.app;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.data.web.RemoteDataSource;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.data.web.WeatherWebService;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Qualifier.WeatherApiKey;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitHelperModule {

    private static final String VERSION_API = "v1/";
    private static final String BASE_URL = "https://api.apixu.com/" + VERSION_API;

    @Provides
    @Singleton
    RemoteDataSource provideApiMapper(WeatherWebService weatherWebService, @WeatherApiKey String weatherApiKey){
        return new RemoteDataSource(weatherWebService, weatherApiKey);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(){
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .build();
        return client;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient client){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    WeatherWebService provideWeatherWebService(Retrofit retrofit){
        return retrofit.create(WeatherWebService.class);
    }

    @Singleton
    @Provides
    @WeatherApiKey
    String provideApiKey() {
        return "66d755c1072e47dcab4161954190202";
    }

    @Singleton
    @Provides
    String kek(){
        return "kek";
    }
}
