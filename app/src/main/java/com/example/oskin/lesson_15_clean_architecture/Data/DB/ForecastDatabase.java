package com.example.oskin.lesson_15_clean_architecture.Data.DB;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.WeatherModel.ForecastDay;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = ForecastDay.class, version = 1)
public abstract class ForecastDatabase extends RoomDatabase {

    public abstract ForecastDAO getForecastDB();

}
