package com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.Repositories.WeatherRepository;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.Entity.DTO.LastRequestInfo;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.Forecast;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.UserPreferences;

public interface ISharedPrefManager {

    UserPreferences getSharedPrefInDTO();
    Forecast.Day getSelectedDay();
    void setSelectedDay(Forecast.Day day);
    void setLastRequest(LastRequestInfo info);
    LastRequestInfo getLastRequest();

}
