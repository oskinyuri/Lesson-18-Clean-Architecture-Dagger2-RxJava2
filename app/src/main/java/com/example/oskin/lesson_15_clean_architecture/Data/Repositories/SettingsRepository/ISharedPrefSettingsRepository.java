package com.example.oskin.lesson_15_clean_architecture.Data.Repositories.SettingsRepository;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.SharedPrefDTO;

public interface ISharedPrefSettingsRepository {
    void loadSharedPref(SharedPrefDTO sharedPrefDTO);
    SharedPrefDTO getSharedPrefInDTO();
}
