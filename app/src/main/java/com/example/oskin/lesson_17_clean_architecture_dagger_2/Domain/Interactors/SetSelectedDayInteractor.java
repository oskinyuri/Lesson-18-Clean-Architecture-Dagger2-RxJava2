package com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.ForecastDTOOutput;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.DIP.IWeatherRepository;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.Callbacks.SetSelectedDayCallback;

public class SetSelectedDayInteractor {

    private IWeatherRepository mRepository;

    public SetSelectedDayInteractor(IWeatherRepository repository){
        mRepository = repository;
    }

    public void setSelectedDay(ForecastDTOOutput.Day day, SetSelectedDayCallback callback){
        mRepository.setSelectedDay(day,callback);
    }
}
