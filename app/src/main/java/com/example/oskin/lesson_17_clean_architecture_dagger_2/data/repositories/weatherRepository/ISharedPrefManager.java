package com.example.oskin.lesson_17_clean_architecture_dagger_2.data.repositories.weatherRepository;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.data.entity.DTO.LastRequestInfo;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.Forecast;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.UserPreferences;

public interface ISharedPrefManager {

    UserPreferences getSharedPrefInDTO();
    Forecast.Day getSelectedDay();
    void setSelectedDay(Forecast.Day day);
    void setLastRequest(LastRequestInfo info);
    LastRequestInfo getLastRequest();

}
