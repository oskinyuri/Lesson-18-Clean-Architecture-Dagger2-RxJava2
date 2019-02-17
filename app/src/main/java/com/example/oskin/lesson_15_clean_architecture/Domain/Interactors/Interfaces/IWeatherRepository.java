package com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.ForecastDTOOutput;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.SharedPrefDTO;

public interface IWeatherRepository {

    void loadWeatherForecast(ILoadDTOCallback callback);

    void setSelectedDay(ForecastDTOOutput.Day day, SetSelectedDayCallback callback);

    void getSelectedDay(GetSelectedDayCallback callback);

}
