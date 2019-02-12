package com.example.oskin.lesson_15_clean_architecture.Data.Repositories;

import android.content.Context;

import com.example.oskin.lesson_15_clean_architecture.Data.DB.DatabaseManager;
import com.example.oskin.lesson_15_clean_architecture.Data.SharedPreferences.SharedPrefManager;
import com.example.oskin.lesson_15_clean_architecture.Data.Web.ApiMapper;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.ForecastDTOInput;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.ForecastDTOOutput;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.SharedPrefDTO;
import com.example.oskin.lesson_15_clean_architecture.Data.Entity.WeatherModel.WeatherModel;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.GetSelectedDayCallback;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.ILoadDTOCallback;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.IRepository;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.GetSharedPrefCallback;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.LoadSharedPrefCallback;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.SetSelectedDayCallback;

public class Repository implements IRepository {

    private Context mContext;

    private ApiMapper mMapper;
    private DatabaseManager mDBManager;
    private ISharedPrefManager mPrefManager;

    //TODO Interface interaction
    private WeatherModel mWeatherModelResponse;
    private ForecastDTOOutput mForecastDTOOutput;
    private SharedPrefDTO mSharedPrefDTOOutput;

    private ILoadDTOCallback mLoadCallback;

    public Repository(Context context){
        mContext = context;

        mMapper = new ApiMapper();
        mDBManager = new DatabaseManager(mContext);
        mPrefManager = new SharedPrefManager(mContext);
    }

    @Override
    public void loadWeatherForecast(ILoadDTOCallback callback) {
        mLoadCallback = callback;
        ForecastDTOInput forecastDTOInput = mPrefManager.getForecastDTOInput();
        WeatherMapper weatherMapper = new WeatherMapper();
        SharedPrefDTO sharedPrefDTO = mPrefManager.getSharedPrefInDTO();


        if (checkNeedLoadFromWeb()){
            mWeatherModelResponse = loadFromWeb(forecastDTOInput);
        }

        if (mWeatherModelResponse == null){
            mLoadCallback.onFailure();
            return;
        }

        mDBManager.addWeatherModel(mWeatherModelResponse);

        mWeatherModelResponse = loadFromDB(forecastDTOInput);
        mForecastDTOOutput = weatherMapper.getDTOFromPOJO(mWeatherModelResponse, sharedPrefDTO);
        mLoadCallback.onResponse(mForecastDTOOutput);
    }

    @Override
    public void getSharedPreferences(GetSharedPrefCallback callback) {
        callback.onResponse(mPrefManager.getSharedPrefInDTO());
    }

    @Override
    public void loadSharedPreferences(SharedPrefDTO sharedPrefDTO, LoadSharedPrefCallback callback) {
        mPrefManager.loadSharedPref(sharedPrefDTO);
        callback.onResponse();
    }

    @Override
    public void setSelectedDay(ForecastDTOOutput.Day day, SetSelectedDayCallback callback) {
        mPrefManager.setSelectedDay(day);
        callback.onResponse();
    }

    @Override
    public void getSelectedDay(GetSelectedDayCallback callback) {
        callback.onResponse(mPrefManager.getSelectedDay());
    }

    private WeatherModel loadFromDB(ForecastDTOInput request) {
        return mDBManager.getWeatherModel(request.getCityName());
    }

    private WeatherModel loadFromWeb(ForecastDTOInput request) {
        return mMapper.loadForecast(request.getCityName(), request.getCountDays());
    }

    private boolean checkNeedLoadFromWeb(){
        long difference = System.currentTimeMillis() - (mPrefManager.getLastTimeLoadInEpoch() * 1000);
        long maxDifference = 5 * 60 * 1000;
        return difference >= maxDifference;
    }


}
