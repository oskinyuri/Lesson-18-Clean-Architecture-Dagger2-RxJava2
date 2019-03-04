package com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.Forecast;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.interfaces.dip.IWeatherRepository;

import io.reactivex.Completable;

public class SetSelectedDayInteractor {

    private IWeatherRepository mRepository;

    public SetSelectedDayInteractor(IWeatherRepository repository){
        mRepository = repository;
    }

    public Completable setSelectedDay(Forecast.Day day){
        return mRepository.setSelectedDay(day);
    }


}
