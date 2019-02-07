package com.example.oskin.lesson_15_clean_architecture.Domain.Interactors;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.WeatherForecastRequest;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.WeatherModel.Forecast;
import com.example.oskin.lesson_15_clean_architecture.WeatherApp;

public class GetWeatherForecast implements IGetCallback{

    private WeatherForecastRequest mRequest;
    private Forecast mForecast;
    private IGetCallback mCallback;
    private IRepository mRepository;

    public GetWeatherForecast(){
        mRepository = WeatherApp.getRepository();
    }

    public void getForecast(WeatherForecastRequest request, IGetCallback callback) {
        mCallback = callback;
        mRequest = request;

        //TODO business logic
    }

    @Override
    public void onResponse(Forecast forecast) {
        mForecast = forecast;
    }

    @Override
    public void onFailure() {

    }
}
