package com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.interfaces.dip;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.Forecast;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.ResponseBundle;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.UserPreferences;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface IWeatherRepository {

    Completable updateData(UserPreferences userPreferences);

    Observable<ResponseBundle<Forecast>> getObservableForecast();

    Completable setSelectedDay(Forecast.Day day);

    Single<Forecast.Day> getSelectedDay();

}
