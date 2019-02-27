package com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.Presenters;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.Forecast;

import java.util.List;

public interface IMainWeekView {

    void setCurrentDay(Forecast.Current currentDay,
                       Forecast.SettingPref settingPref);
    void setDataIntoAdapter(List<Forecast.Day> dayList);
    void makeToast(String text);
    void startProgress();
    void hideProgress();
    void starNewScreen();
}
