package com.example.oskin.lesson_17_clean_architecture_dagger_2.data.db;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.data.entity.WeatherModel.WeatherModel;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {WeatherModel.class}, version = 1)
@TypeConverters(value = DataConverter.class)
public abstract class ForecastDatabase extends RoomDatabase {

    public abstract ForecastDAO getForecastDB();

}
