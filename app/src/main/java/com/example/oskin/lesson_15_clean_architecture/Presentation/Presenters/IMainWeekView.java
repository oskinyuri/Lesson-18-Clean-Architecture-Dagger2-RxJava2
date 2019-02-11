package com.example.oskin.lesson_15_clean_architecture.Presentation.Presenters;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.ForecastDTOOutput;

import java.util.List;

public interface IMainWeekView {

    void setCurrentDay(ForecastDTOOutput.Current currentDay,
                       ForecastDTOOutput.SettingPref settingPref);
    void setDataIntoAdapter(List<ForecastDTOOutput.Day> dayList);
    void makeToast(String text);
    void startProgress();
    void hideProgress();
    void starNewScreen();
}
