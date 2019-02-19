package com.example.oskin.lesson_15_clean_architecture.Presentation.Presenters;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.ForecastDTOOutput;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.UserPreferences;

public interface IDayView {
    void setSharedPrefDto(UserPreferences prefDTO);
    void setSelectedDay(ForecastDTOOutput.Day day);
    void displayData();
}
