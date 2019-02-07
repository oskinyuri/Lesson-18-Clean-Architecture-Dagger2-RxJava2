package com.example.oskin.lesson_15_clean_architecture.Domain.Interactors;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.WeatherForecastRequest;

public interface IRepository {

    void loadWeatherForecast(WeatherForecastRequest request, ILoadCallback callback);

    void getWeatherForecast(WeatherForecastRequest request, ILoadCallback callback);



}
