package com.example.oskin.lesson_15_clean_architecture.Domain.Interactors;

import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.GetSelectedDayCallback;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.IRepository;

public class GetSelectedDay {

    private IRepository mRepository;

    public GetSelectedDay(IRepository repository) {
        mRepository = repository;
    }

    public void getSelectedDay(GetSelectedDayCallback callback){
        mRepository.getSelectedDay(callback);
    }
}
