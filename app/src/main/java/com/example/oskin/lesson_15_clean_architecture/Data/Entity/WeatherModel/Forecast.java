package com.example.oskin.lesson_15_clean_architecture.Data.Entity.WeatherModel;

import com.example.oskin.lesson_15_clean_architecture.Data.DB.DataConverter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.TypeConverters;

public class Forecast {

    @SerializedName("forecastday")
    @TypeConverters(DataConverter.class)
    @ColumnInfo
    public List<ForecastDay> mForecastDayList;

    public List<ForecastDay> getForecastDayList() {
        return mForecastDayList;
    }

    public void setForecastDayList(List<ForecastDay> forecastDayList) {
        this.mForecastDayList = forecastDayList;
    }
}
