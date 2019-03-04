package com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.Forecast;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.interfaces.dip.IWeatherRepository;

import io.reactivex.Completable;
import io.reactivex.Single;

public class SelectedDayInteractor {

    private IWeatherRepository mRepository;

    public SelectedDayInteractor(IWeatherRepository repository){
        mRepository = repository;
    }

    public Completable setSelectedDay(Forecast.Day day){
        return mRepository.setSelectedDay(day);
    }

    public Single<Forecast.Day> getSelectedDay(){
        return mRepository.getSelectedDay();
    }
}
