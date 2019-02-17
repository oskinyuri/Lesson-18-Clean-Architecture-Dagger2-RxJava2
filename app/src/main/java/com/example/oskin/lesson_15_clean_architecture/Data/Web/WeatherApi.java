package com.example.oskin.lesson_15_clean_architecture.Data.Web;

import com.example.oskin.lesson_15_clean_architecture.Data.Entity.WeatherModel.WeatherModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET(value = "forecast.json")
    Call<WeatherModel> getForecast (
            @Query("key") String key,
            @Query("q") String cityName,
            @Query("days") int days);


}
