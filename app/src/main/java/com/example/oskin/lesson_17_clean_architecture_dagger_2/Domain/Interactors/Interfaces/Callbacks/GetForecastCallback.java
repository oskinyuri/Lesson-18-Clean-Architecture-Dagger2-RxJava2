package com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.Callbacks;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.Forecast;

public interface GetForecastCallback {

    void onResponse(Forecast dtoOutput);
    void onFailure();
}
