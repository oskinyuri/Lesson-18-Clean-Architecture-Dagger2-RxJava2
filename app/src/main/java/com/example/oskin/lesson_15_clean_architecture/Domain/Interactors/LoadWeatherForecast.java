package com.example.oskin.lesson_15_clean_architecture.Domain.Interactors;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.ForecastDTOInput;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.ForecastDTOOutput;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.ILoadDTOCallback;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.IRepository;

public class LoadWeatherForecast implements ILoadDTOCallback {

    private ILoadDTOCallback mCallback;
    private ForecastDTOInput mRequest;
    private IRepository mRepository;

    public LoadWeatherForecast(IRepository repository){
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
