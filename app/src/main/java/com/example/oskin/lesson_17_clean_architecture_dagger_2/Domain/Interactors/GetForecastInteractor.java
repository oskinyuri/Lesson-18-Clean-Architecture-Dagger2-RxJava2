package com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.Forecast;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.ResponseBundle;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.DIP.IWeatherRepository;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class GetForecastInteractor {

    private IWeatherRepository mRepository;

    public GetForecastInteractor(IWeatherRepository repository) {
        mRepository = repository;
    }

    /**
     * @return Горячий observable, который будет отдавать данные по мере их излучения.
     */
    public Observable<ResponseBundle<Forecast>> getObservableForecast() {
        /**
         * Some interactor logic
         */
        return mRepository.getObservableForecast();
    }

    /**
     * Обновления данных. Данные придут на заранее полученый горячий observable.
     */
    public Completable updateData() {
        return mRepository.updateData();
    }

}
