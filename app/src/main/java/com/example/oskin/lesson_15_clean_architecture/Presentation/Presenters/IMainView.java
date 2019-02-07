package com.example.oskin.lesson_15_clean_architecture.Presentation.Presenters;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.WeatherForecastRequest;

public interface IMainView {

    void setCountDays(int countDays);

    void setCityName(String cityName);

    void starNewScreen(WeatherForecastRequest request);

    void makeToast(String text);

    void startProgress();

    void hideProgress();
}
