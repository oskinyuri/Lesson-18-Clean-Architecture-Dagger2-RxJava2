package com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.interfaces.callbacks.GetSelectedDayCallback;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.interfaces.dip.IWeatherRepository;

public class GetSelectedDayInteractor {

    private IWeatherRepository mRepository;

    public GetSelectedDayInteractor(IWeatherRepository repository) {
        mRepository = repository;
    }

    public void getSelectedDay(GetSelectedDayCallback callback){
        mRepository.getSelectedDay(callback);
    }
}
