package com.example.oskin.lesson_17_clean_architecture_dagger_2.data.db;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.data.entity.WeatherModel.ForecastDay;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

import androidx.room.TypeConverter;

public class DataConverter implements Serializable {

    @TypeConverter
    public String fromForecastDayList(List<ForecastDay> forecastDayList){
        if (forecastDayList == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<ForecastDay>>() {
        }.getType();
        String json = gson.toJson(forecastDayList,type);
        return json;
    }

    @TypeConverter
    public List<ForecastDay> toForecastDatList(String forecastDayString){
        if (forecastDayString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<ForecastDay>>() {
        }.getType();
        return gson.fromJson(forecastDayString, type);
    }
}
