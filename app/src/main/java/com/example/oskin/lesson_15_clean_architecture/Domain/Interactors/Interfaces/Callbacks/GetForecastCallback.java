package com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.Callbacks;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.ForecastDTOOutput;

public interface GetForecastCallback {

    void onResponse(ForecastDTOOutput dtoOutput);
    void onFailure();
}
