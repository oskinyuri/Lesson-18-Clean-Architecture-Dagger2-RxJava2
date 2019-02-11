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

    @GET("https://api.apixu.com/v1/forecast.json?key=66d755c1072e47dcab4161954190202&q=Moscow&days=1")
    Call<WeatherModel> getForecastTest();
}
