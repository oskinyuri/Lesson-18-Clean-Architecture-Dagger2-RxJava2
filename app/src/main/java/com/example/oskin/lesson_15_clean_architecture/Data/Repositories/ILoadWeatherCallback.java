package com.example.oskin.lesson_15_clean_architecture.Data.Repositories;

import com.example.oskin.lesson_15_clean_architecture.Data.Entity.WeatherModel.WeatherModel;

public interface ILoadWeatherCallback {

        void onResponse(WeatherModel weatherModel);
        void onFailure();

}
