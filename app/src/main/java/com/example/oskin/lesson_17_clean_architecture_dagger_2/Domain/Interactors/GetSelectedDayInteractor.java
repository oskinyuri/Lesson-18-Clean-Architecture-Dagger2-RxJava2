package com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.Callbacks.GetSelectedDayCallback;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.DIP.IWeatherRepository;

public class GetSelectedDayInteractor {

    private IWeatherRepository mRepository;

    public GetSelectedDayInteractor(IWeatherRepository repository) {
        mRepository = repository;
    }

    public void getSelectedDay(GetSelectedDayCallback callback){
        mRepository.getSelectedDay(callback);
    }
}
