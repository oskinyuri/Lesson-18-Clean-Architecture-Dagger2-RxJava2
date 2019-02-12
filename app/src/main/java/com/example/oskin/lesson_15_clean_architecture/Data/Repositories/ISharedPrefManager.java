package com.example.oskin.lesson_15_clean_architecture.Data.Repositories;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.ForecastDTOInput;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.ForecastDTOOutput;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.SharedPrefDTO;

public interface ISharedPrefManager {

    SharedPrefDTO getSharedPrefInDTO();
    ForecastDTOInput getForecastDTOInput();
    ForecastDTOOutput.Day getSelectedDay();
    void setSelectedDay(ForecastDTOOutput.Day day);
    void loadSharedPref(SharedPrefDTO sharedPrefDTO);
    void setLastTimeLoadInEpoch(long timeInEpoch);
    long getLastTimeLoadInEpoch();

}
