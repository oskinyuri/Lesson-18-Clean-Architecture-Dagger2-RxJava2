package com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.Forecast;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.ResponseBundle;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.DIP.IWeatherRepository;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.Callbacks.SetSelectedDayCallback;

import io.reactivex.Completable;
import io.reactivex.Single;

public class SetSelectedDayInteractor {

    private IWeatherRepository mRepository;

    public SetSelectedDayInteractor(IWeatherRepository repository){
        mRepository = repository;
    }

    public Completable setSelectedDay(Forecast.Day day){
        return mRepository.setSelectedDay(day);
    }


}
