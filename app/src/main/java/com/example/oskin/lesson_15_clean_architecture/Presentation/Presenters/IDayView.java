package com.example.oskin.lesson_15_clean_architecture.Presentation.Presenters;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.ForecastDTOOutput;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.SharedPrefDTO;

public interface IDayView {
    void setSharedPrefDto(SharedPrefDTO prefDTO);
    void setSelectedDay(ForecastDTOOutput.Day day);
    void displayData();
}
