package com.example.oskin.lesson_15_clean_architecture.Data.Repositories.WeatherRepository;

import com.example.oskin.lesson_15_clean_architecture.Data.Entity.DTO.LastRequestInfo;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.ForecastDTOOutput;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.SharedPrefDTO;

public interface ISharedPrefWeatherManager {

    SharedPrefDTO getSharedPrefInDTO();
    ForecastDTOOutput.Day getSelectedDay();
    void setSelectedDay(ForecastDTOOutput.Day day);
    void setLastRequest(LastRequestInfo info);
    LastRequestInfo getLastRequest();

}
