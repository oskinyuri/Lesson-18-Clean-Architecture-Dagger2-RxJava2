package com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.DB;

import android.content.Context;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.Utils.UtilDate;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.Entity.WeatherModel.WeatherModel;

import androidx.room.Room;

public class DatabaseManager {

    private static String FORECAST_DATABASE_NAME = "FORECAST_DATABASE";

    private ForecastDatabase db;

    public DatabaseManager(Context context) {
        db = Room.databaseBuilder(context, ForecastDatabase.class, FORECAST_DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    public void addWeatherModel(WeatherModel weatherModel){

        /**
         * Удаление записей страше сегодняшнего числа
         */
        db.getForecastDB().deleteWeatherModel(UtilDate.getTodayEpoch());

        db.getForecastDB().addWeatherModel(weatherModel);
    }

    public WeatherModel getForecast(String cityName){
        return db.getForecastDB().getWeatherModel(cityName);
    }


}
