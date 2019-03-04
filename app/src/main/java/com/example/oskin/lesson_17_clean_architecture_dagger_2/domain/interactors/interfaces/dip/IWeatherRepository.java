package com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.interfaces.dip;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.Forecast;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.ResponseBundle;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.interfaces.callbacks.GetSelectedDayCallback;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

public interface IWeatherRepository {

    Completable updateData();

    Observable<ResponseBundle<Forecast>> getObservableForecast();

    Completable setSelectedDay(Forecast.Day day);

    void getSelectedDay(GetSelectedDayCallback callback);

}
