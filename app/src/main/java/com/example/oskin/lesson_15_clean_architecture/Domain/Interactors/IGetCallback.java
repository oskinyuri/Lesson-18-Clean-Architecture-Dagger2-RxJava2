package com.example.oskin.lesson_15_clean_architecture.Domain.Interactors;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.WeatherModel.Forecast;

public interface IGetCallback {

    void onResponse(Forecast forecast);
    void onFailure();

}
