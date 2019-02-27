package com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.Presenters;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.Forecast;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.UserPreferences;

public interface IDayView {
    void setSharedPrefDto(UserPreferences prefDTO);
    void setSelectedDay(Forecast.Day day);
    void displayData();
}
