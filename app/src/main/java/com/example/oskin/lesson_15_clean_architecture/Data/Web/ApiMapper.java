package com.example.oskin.lesson_15_clean_architecture.Data.Web;

import com.example.oskin.lesson_15_clean_architecture.Data.Entity.WeatherModel.WeatherModel;

import java.io.IOException;

public class ApiMapper {

    private RetrofitHelper mRetrofitHelper;

    public ApiMapper() {
        mRetrofitHelper = new RetrofitHelper();
    }

    public WeatherModel loadForecast(final String city, int days) {
        try {
            WeatherModel weatherModel = mRetrofitHelper.getService()
                    .getForecast(RetrofitHelper.KEY, city, days).execute().body();
            return weatherModel;
        } catch (IOException e) {
            return null;
        }
    }
}
