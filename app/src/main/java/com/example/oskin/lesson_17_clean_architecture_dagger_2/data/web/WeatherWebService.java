package com.example.oskin.lesson_17_clean_architecture_dagger_2.data.web;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.data.entity.WeatherModel.WeatherModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherWebService {
    @GET(value = "forecast.json")
    Call<WeatherModel> getForecast (
            @Query("key") String key,
            @Query("q") String cityName,
            @Query("lang") String language,
            @Query("days") int days);


}
