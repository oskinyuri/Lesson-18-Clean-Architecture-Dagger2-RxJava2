package com.example.oskin.lesson_15_clean_architecture.Presentation.Presenters;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.WeatherForecastRequest;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.WeatherModel.Forecast;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.GetWeatherForecast;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.IGetCallback;

public class ForecastPresenter implements IGetCallback {

    public static String REQUEST_KEY= "FORECAST_REQUEST";

    private IForecastView mView;
    private GetWeatherForecast mGetWeatherForecast;
    private WeatherForecastRequest mRequest;
    private Forecast mForecast;

    public void onAttach(IForecastView view){
        mView = view;
    }

    public void onDetach(){
        mView = null;
    }

    public void getForecast(){
        mGetWeatherForecast = new GetWeatherForecast();
        mGetWeatherForecast.getForecast(mRequest, this);
    }

    public void setRequest(WeatherForecastRequest request){
        mRequest = request;
    }

    @Override
    public void onResponse(Forecast forecast) {
        mForecast = forecast;
    }

    @Override
    public void onFailure() {

    }
}
