package com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.presenters;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.Forecast;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.UserPreferences;

public interface IDayView {
    void setSharedPrefDto(UserPreferences prefDTO);
    void setSelectedDay(Forecast.Day day);
    void displayData();
}
