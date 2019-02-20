package com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.ForecastDTOOutput;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.Callbacks.GetForecastCallback;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.DIP.IWeatherRepository;

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
