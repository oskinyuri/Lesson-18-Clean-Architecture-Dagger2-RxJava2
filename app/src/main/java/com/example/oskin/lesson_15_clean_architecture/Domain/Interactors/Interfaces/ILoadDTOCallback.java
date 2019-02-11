package com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.ForecastDTOOutput;

public interface ILoadDTOCallback {

    void onResponse(ForecastDTOOutput dtoOutput);
    void onFailure();
}
