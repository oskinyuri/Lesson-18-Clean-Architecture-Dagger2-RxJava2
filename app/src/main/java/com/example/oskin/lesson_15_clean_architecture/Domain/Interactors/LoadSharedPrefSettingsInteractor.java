package com.example.oskin.lesson_15_clean_architecture.Domain.Interactors;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.SharedPrefDTO;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.ISettingsRepository;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.IWeatherRepository;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.LoadSharedPrefCallback;

public class LoadSharedPrefSettingsInteractor {

    private ISettingsRepository mRepository;
    private SharedPrefDTO mDTOOutput;

    public LoadSharedPrefSettingsInteractor(ISettingsRepository repository) {
        mRepository = repository;
    }

    public void loadSharedPreferences(SharedPrefDTO prefDTO, LoadSharedPrefCallback callback){
        mRepository.loadSharedPreferences(prefDTO, callback);
    }
}
