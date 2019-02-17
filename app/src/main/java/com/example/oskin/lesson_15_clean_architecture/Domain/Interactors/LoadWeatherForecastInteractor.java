package com.example.oskin.lesson_15_clean_architecture.Domain.Interactors;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.ForecastDTOOutput;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.ILoadDTOCallback;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.IWeatherRepository;

public class LoadWeatherForecastInteractor implements ILoadDTOCallback {

    private ILoadDTOCallback mCallback;
    private IWeatherRepository mRepository;

    public LoadWeatherForecastInteractor(IWeatherRepository repository){
        mRepository = repository;
    }

    public void loadForecast(ILoadDTOCallback callback) {
        mCallback = callback;

        mRepository.loadWeatherForecast(this);
    }

    @Override
    public void onResponse(ForecastDTOOutput dtoOutput) {
        /**
         * Some interactor logic
         */
        mCallback.onResponse(dtoOutput);
    }

    @Override
    public void onFailure() {
        /**
         * Some interactor logic
         */
        mCallback.onFailure();
    }
}
