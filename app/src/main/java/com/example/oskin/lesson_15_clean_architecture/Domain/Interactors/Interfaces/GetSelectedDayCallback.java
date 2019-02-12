package com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.ForecastDTOOutput;

public interface GetSelectedDayCallback {
    void onResponse(ForecastDTOOutput.Day day);
}
