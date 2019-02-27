package com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors;


import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.Forecast;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.ResponseBundle;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.Callbacks.GetForecastCallback;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.DIP.IWeatherRepository;

import io.reactivex.Observable;

public class GetForecastInteractor implements GetForecastCallback {

    private GetForecastCallback mCallback;
    private IWeatherRepository mRepository;

    public GetForecastInteractor(IWeatherRepository repository){
        mRepository = repository;
    }

    //Новый метод
    public Observable<ResponseBundle<Forecast>> loadForecast(){
        /**
         * Some interactor logic
         */
        return mRepository.loadWeatherForecast();
    }

    @Override
    public void onResponse(Forecast dtoOutput) {
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
