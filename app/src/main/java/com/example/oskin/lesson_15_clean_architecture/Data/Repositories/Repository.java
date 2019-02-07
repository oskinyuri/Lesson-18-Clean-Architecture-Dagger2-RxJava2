package com.example.oskin.lesson_15_clean_architecture.Data.Repositories;

import android.content.Context;

import com.example.oskin.lesson_15_clean_architecture.Data.DB.DatabaseManager;
import com.example.oskin.lesson_15_clean_architecture.Data.Web.ApiMapper;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.WeatherForecastRequest;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.Utils.UtilDate;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.WeatherModel.Forecast;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.WeatherModel.ForecastDay;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.IGetCallback;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.ILoadCallback;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.IRepository;

public class Repository implements IRepository {

    private ApiMapper mMapper;
    private DatabaseManager mDBManager;
    private WeatherForecastRequest mRequest;
    private Forecast mForecastResponse;
    private ILoadCallback mCallback;

    public Repository(Context context){
        mMapper = new ApiMapper();
        mDBManager = new DatabaseManager(context);
    }

    @Override
    public void loadWeatherForecast(WeatherForecastRequest request, ILoadCallback callback) {
        mRequest = request;
        mCallback = callback;

        //TODO logic
        /**
         * Some repository logic for choose database or web
         */

        final String cityName = mRequest.getCityName();
        int countDays = mRequest.getCountDays();

        mMapper.loadForecast(cityName, countDays, new IGetCallback(){
            @Override
            public void onResponse(Forecast forecast) {
                /**
                 * Some response logic*/

                mForecastResponse = forecast;
                for (ForecastDay f: mForecastResponse.getForecastDayList()){
                    //TODO getting cityName from weatherModel
                    f.setCityName(cityName);
                    f.setDayOfWeek(UtilDate.getDayOfWeek(f.getDate_epoch()));
                }

                mDBManager.addForecast(mForecastResponse.getForecastDayList());
                mCallback.onResponse();
            }

            @Override
            public void onFailure() {
                /**
                 * Some failure logic*/

                mCallback.onFailure();

            }
        });
    }

    @Override
    public void getWeatherForecast(WeatherForecastRequest request, ILoadCallback callback) {

    }
}
