package com.example.oskin.lesson_15_clean_architecture.Domain.Interactors;

import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.GetSelectedDayCallback;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.IWeatherRepository;

public class GetSelectedDay {

    private IWeatherRepository mRepository;

    public GetSelectedDay(IWeatherRepository repository) {
        mRepository = repository;
    }

    public void getSelectedDay(GetSelectedDayCallback callback){
        mRepository.getSelectedDay(callback);
    }
}
