package com.example.oskin.lesson_15_clean_architecture.Domain.Interactors;

import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.Callbacks.GetSelectedDayCallback;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.DIP.IWeatherRepository;

public class GetSelectedDayInteractor {

    private IWeatherRepository mRepository;

    public GetSelectedDayInteractor(IWeatherRepository repository) {
        mRepository = repository;
    }

    public void getSelectedDay(GetSelectedDayCallback callback){
        mRepository.getSelectedDay(callback);
    }
}
