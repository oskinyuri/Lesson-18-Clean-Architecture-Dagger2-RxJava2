package com.example.oskin.lesson_15_clean_architecture.Data.DB;

import android.content.Context;

import com.example.oskin.lesson_15_clean_architecture.Data.Entity.WeatherModel.ForecastDay;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.Utils.UtilDate;
import com.example.oskin.lesson_15_clean_architecture.Data.Entity.WeatherModel.Forecast;
import com.example.oskin.lesson_15_clean_architecture.Data.Entity.WeatherModel.WeatherModel;

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
        weatherModel.setCityName(weatherModel.getLocation().getName());

        /**
         * Сетинг дня недели в каждый день.
         */
        for (ForecastDay f: weatherModel.getForecast().getForecastDayList()) {
            f.setDayOfWeek(UtilDate.getDayOfWeek(f.getDateEpoch()));
        }

        /**
         * Удаление записей страше сегодняшнего числа
         */
        db.getForecastDB().deleteWeatherModel(UtilDate.getTodayEpoch());

        db.getForecastDB().addWeatherModel(weatherModel);
    }

    public WeatherModel getWeatherModel(String cityName){
        return db.getForecastDB().getWeatherModel(cityName);
    }


}
