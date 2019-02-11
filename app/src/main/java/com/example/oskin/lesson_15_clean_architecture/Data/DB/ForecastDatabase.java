package com.example.oskin.lesson_15_clean_architecture.Data.DB;

import com.example.oskin.lesson_15_clean_architecture.Data.Entity.WeatherModel.WeatherModel;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {WeatherModel.class}, version = 1)
@TypeConverters(value = DataConverter.class)
public abstract class ForecastDatabase extends RoomDatabase {

    public abstract ForecastDAO getForecastDB();

}
