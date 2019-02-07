package com.example.oskin.lesson_15_clean_architecture.Presentation.Presenters;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.WeatherModel.ForecastDay;
import java.util.List;

public interface IForecastView {

    void setForecast(List<ForecastDay> forecastDayList);

    void makeToast(String text);

    void startProgress();

    void hideProgress();
}
