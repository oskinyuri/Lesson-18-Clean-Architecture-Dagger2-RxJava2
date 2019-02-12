package com.example.oskin.lesson_15_clean_architecture.Domain.Interactors;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.ForecastDTOOutput;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.IRepository;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.SetSelectedDayCallback;

public class SetSelectedDay {

    private IRepository mRepository;

    public SetSelectedDay(IRepository repository){
        mRepository = repository;
    }

    public void setSelectedDay(ForecastDTOOutput.Day day, SetSelectedDayCallback callback){
        mRepository.setSelectedDay(day,callback);
    }
}
