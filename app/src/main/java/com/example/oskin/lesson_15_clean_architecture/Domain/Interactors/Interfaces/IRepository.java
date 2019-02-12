package com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.ForecastDTOOutput;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.SharedPrefDTO;

public interface IRepository {

    void loadWeatherForecast(ILoadDTOCallback callback);

    void getSharedPreferences(GetSharedPrefCallback callback);

    void loadSharedPreferences(SharedPrefDTO dtoOutput, LoadSharedPrefCallback callback);

    void setSelectedDay(ForecastDTOOutput.Day day, SetSelectedDayCallback callback);

    void getSelectedDay(GetSelectedDayCallback callback);

}
