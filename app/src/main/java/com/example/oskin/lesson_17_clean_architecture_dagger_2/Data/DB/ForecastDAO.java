package com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.DB;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.Entity.WeatherModel.WeatherModel;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface ForecastDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addWeatherModel(WeatherModel weatherModel);

    @Query("select * from WeatherModel where location_name = :cityName")
    WeatherModel getWeatherModel(String cityName);

    @Query("delete from WeatherModel where current_last_updated_epoch < :todayEpoch")
    void deleteWeatherModel(long todayEpoch);
}
