package com.example.oskin.lesson_15_clean_architecture.Domain.Interactors;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.ForecastDTOOutput;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.Callbacks.GetForecastCallback;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.DIP.IWeatherRepository;

public class GetForecastInteractor implements GetForecastCallback {

    private GetForecastCallback mCallback;
    private IWeatherRepository mRepository;

    public GetForecastInteractor(IWeatherRepository repository){
        mRepository = repository;
    }

    public void loadForecast(GetForecastCallback callback) {
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
