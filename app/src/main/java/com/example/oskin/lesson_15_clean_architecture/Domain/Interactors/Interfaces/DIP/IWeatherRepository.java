package com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.DIP;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.ForecastDTOOutput;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.Callbacks.GetForecastCallback;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.Callbacks.GetSelectedDayCallback;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.Callbacks.SetSelectedDayCallback;

public interface IWeatherRepository {

    void loadWeatherForecast(GetForecastCallback callback);

    void setSelectedDay(ForecastDTOOutput.Day day, SetSelectedDayCallback callback);

    void getSelectedDay(GetSelectedDayCallback callback);

}
