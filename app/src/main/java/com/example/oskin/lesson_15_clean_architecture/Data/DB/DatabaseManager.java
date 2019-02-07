package com.example.oskin.lesson_15_clean_architecture.Data.DB;

import android.content.Context;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.Utils.UtilDate;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.WeatherModel.ForecastDay;

import java.util.List;
import androidx.room.Room;

public class DatabaseManager {

    private static String FORECAST_DATABASE_NAME = "FORECAST_DATABASE";

    ForecastDatabase db;

    public DatabaseManager(Context context) {
        db = Room.databaseBuilder(context, ForecastDatabase.class, FORECAST_DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    public void getForecast(String city, int countDays){
        db.getForecastDB().getForecast(city, countDays);
    }

    public void addForecast(List<ForecastDay> forecastDays){
        deleteOldForecast();
        db.getForecastDB().addForecast(forecastDays);
    }

    public void updateForecast(List<ForecastDay> forecastDays){
        db.getForecastDB().updateNote(forecastDays);
    }

    public void deleteForecast(){
        db.getForecastDB().deleteForecast();
    }

    private void deleteOldForecast(){
        db.getForecastDB().deleteForecast(UtilDate.getTodayEpoch());
    }


}
