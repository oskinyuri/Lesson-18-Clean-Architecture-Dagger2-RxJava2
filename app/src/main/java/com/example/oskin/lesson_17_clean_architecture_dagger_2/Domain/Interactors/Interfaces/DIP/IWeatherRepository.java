package com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.DIP;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.Forecast;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.ResponseBundle;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.Callbacks.GetSelectedDayCallback;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.Callbacks.SetSelectedDayCallback;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface IWeatherRepository {

    //void updateData();

    Completable updateData();

    Observable<ResponseBundle<Forecast>> getObservableForecast();

    //Single<ResponseBundle<Boolean>> setSelectedDay(Forecast.Day day);

    Completable setSelectedDay(Forecast.Day day);

    void getSelectedDay(GetSelectedDayCallback callback);

}
