package com.example.oskin.lesson_15_clean_architecture.Data.DB;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.WeatherModel.ForecastDay;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ForecastDAO {

    @Query("select * from ForecastDay where cityName = :cityName limit :countDays")
    List<ForecastDay> getForecast(String cityName, int countDays);

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void addForecast(List<ForecastDay> forecastDays);

    @Update
    void updateNote(List<ForecastDay> forecastDays);

    @Query("delete from ForecastDay")
    void deleteForecast();

    @Query("delete from ForecastDay where date_epoch < :todayEpoch")
    void deleteForecast(long todayEpoch);




}
