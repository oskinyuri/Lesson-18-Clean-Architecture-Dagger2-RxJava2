package com.example.oskin.lesson_15_clean_architecture.Data.Web;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.Utils.UtilDate;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.WeatherModel.ForecastDay;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.WeatherModel.WeatherModel;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.IGetCallback;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.ILoadCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiMapper {

    private RetrofitHelper mRetrofitHelper;
    private WeatherModel mWeatherModel;
    private IGetCallback mCallback;

    public ApiMapper() {
        mRetrofitHelper = new RetrofitHelper();
    }

    public void loadForecast(final String city, int days, final IGetCallback callback){
        mCallback = callback;
        mRetrofitHelper.getService().getForecast(RetrofitHelper.KEY,city,days).enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                if (response.body() == null)
                    return;
                mWeatherModel = response.body();
                callback.onResponse(mWeatherModel.getForecast());
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
                callback.onFailure();
            }
        });
    }
}
