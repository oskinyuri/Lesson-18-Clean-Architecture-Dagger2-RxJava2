package com.example.oskin.lesson_15_clean_architecture.Data.Web;

import com.example.oskin.lesson_15_clean_architecture.Data.Entity.WeatherModel.WeatherModel;
import com.example.oskin.lesson_15_clean_architecture.Presentation.DI.Qualifier.WeatherApiKey;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;

public class ApiMapper {

    private WeatherWebService mWeatherWebService;
    private String mApiKey;

    public ApiMapper(WeatherWebService weatherWebService, String apiKey) {
        mApiKey = apiKey;
        mWeatherWebService = weatherWebService;
    }

    public WeatherModel loadForecast(final String city, int days) {
        try {
            WeatherModel weatherModel = mWeatherWebService
                    .getForecast(mApiKey, city, days).execute().body();
            return weatherModel;
        } catch (IOException e) {
            return null;
        }
    }
}
