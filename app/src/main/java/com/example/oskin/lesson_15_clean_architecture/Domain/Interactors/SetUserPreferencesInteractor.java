package com.example.oskin.lesson_15_clean_architecture.Domain.Interactors;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.UserPreferences;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.DIP.ISettingsRepository;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.Callbacks.SetUserPrefCallback;

public class SetUserPreferencesInteractor {

    private ISettingsRepository mRepository;

    public SetUserPreferencesInteractor(ISettingsRepository repository) {
        mRepository = repository;
    }

    public void setUserPreferences(UserPreferences preferences, SetUserPrefCallback callback){
        mRepository.setUserPreferences(preferences, callback);
    }
}
