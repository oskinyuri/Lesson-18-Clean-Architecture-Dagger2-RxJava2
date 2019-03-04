package com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.interfaces.callbacks;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.Forecast;

public interface GetForecastCallback {

    void onResponse(Forecast dtoOutput);
    void onFailure();
}
