package com.example.oskin.lesson_15_clean_architecture.Domain.Interactors;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.WeatherForecastRequest;
import com.example.oskin.lesson_15_clean_architecture.WeatherApp;

public class LoadWeatherForecast implements ILoadCallback {

    private ILoadCallback mCallback;
    private WeatherForecastRequest mRequest;
    private IRepository mRepository;

    public LoadWeatherForecast() {
        mRepository = WeatherApp.getRepository();
    }

    public void loadForecast(WeatherForecastRequest request, ILoadCallback callback) {
        mCallback = callback;
        mRequest = request;

        mRepository.loadWeatherForecast(mRequest, this);
        //TODO load from repo
    }

    @Override
    public void onResponse() {
        /**
         * Some interactor logic
         */
        mCallback.onResponse();
    }

    @Override
    public void onFailure() {
        /**
         * Some interactor logic
         */
        mCallback.onFailure();
    }
}
