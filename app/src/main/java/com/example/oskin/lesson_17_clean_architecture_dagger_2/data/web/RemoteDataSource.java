package com.example.oskin.lesson_17_clean_architecture_dagger_2.data.web;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.data.entity.WeatherModel.WeatherModel;

import java.io.IOException;

public class RemoteDataSource {

    private WeatherWebService mWeatherWebService;
    private String mApiKey;

    public RemoteDataSource(WeatherWebService weatherWebService, String apiKey) {
        mApiKey = apiKey;
        mWeatherWebService = weatherWebService;
    }

    public WeatherModel getForecast(final String city, int days) {
        try {
            WeatherModel weatherModel = mWeatherWebService
                    .getForecast(mApiKey, city, "ru",days).execute().body();
            return weatherModel;
        } catch (IOException e) {
            return null;
        }
    }
}
