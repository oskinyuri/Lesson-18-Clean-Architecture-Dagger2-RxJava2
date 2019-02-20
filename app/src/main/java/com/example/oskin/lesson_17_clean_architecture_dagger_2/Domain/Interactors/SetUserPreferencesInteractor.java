package com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.UserPreferences;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.DIP.ISettingsRepository;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.Callbacks.SetUserPrefCallback;

public class SetUserPreferencesInteractor {

    private ISettingsRepository mRepository;

    public SetUserPreferencesInteractor(ISettingsRepository repository) {
        mRepository = repository;
    }

    public void setUserPreferences(UserPreferences preferences, SetUserPrefCallback callback){
        mRepository.setUserPreferences(preferences, callback);
    }
}
