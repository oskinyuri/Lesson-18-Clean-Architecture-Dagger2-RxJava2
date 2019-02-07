package com.example.oskin.lesson_15_clean_architecture.Domain.Entity.WeatherModel;

import com.google.gson.annotations.SerializedName;

public class WeatherModel {
    @SerializedName("forecast")
    public Forecast forecast;

    //TODO location field

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }
}
