package com.example.oskin.lesson_15_clean_architecture.Domain.Entity.WeatherModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Forecast {
    @SerializedName("forecastday")
    public List<ForecastDay> mForecastDayList;

    public List<ForecastDay> getForecastDayList() {
        return mForecastDayList;
    }

    public void setForecastDayList(List<ForecastDay> forecastDayList) {
        this.mForecastDayList = forecastDayList;
    }


}
