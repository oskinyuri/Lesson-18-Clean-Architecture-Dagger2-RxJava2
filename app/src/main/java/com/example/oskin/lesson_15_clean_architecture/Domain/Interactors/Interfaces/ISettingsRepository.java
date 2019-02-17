package com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.SharedPrefDTO;

public interface ISettingsRepository {

    void getSharedPreferences(GetSharedPrefCallback callback);

    void loadSharedPreferences(SharedPrefDTO dtoOutput, LoadSharedPrefCallback callback);

}
