package com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.Forecast;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.ResponseBundle;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.UserPreferences;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.interfaces.dip.IWeatherRepository;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

public class GetForecastInteractor {

    private IWeatherRepository mRepository;

    public GetForecastInteractor(IWeatherRepository repository) {
        mRepository = repository;
    }

    /**
     * @return Горячий observable, который будет отдавать данные после запроса.
     */
    public Observable<ResponseBundle<Forecast>> getObservableForecast() {
        return mRepository.getObservableForecast();
    }

    /**
     * Обновления данных. Данные придут на заранее полученый горячий observable.
     */
    public Completable updateData(UserPreferences userPreferences) {
        return mRepository.updateData(userPreferences);
    }

}
