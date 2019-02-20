package com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.UserPreferences;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.Callbacks.GetUserPrefCallback;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.DIP.ISettingsRepository;

public class GetUserPreferencesInteractor implements GetUserPrefCallback {

    private ISettingsRepository mRepository;
    private GetUserPrefCallback mPrefCallback;
    private UserPreferences mPreferences;

    public GetUserPreferencesInteractor(ISettingsRepository repository) {
        mRepository = repository;
    }

    public void getUserPref(GetUserPrefCallback prefCallback){
        mPrefCallback = prefCallback;
        mRepository.getUserPreferences(this);
    }

    @Override
    public void onResponse(UserPreferences userPreferences) {
        mPreferences = userPreferences;
        /**
         * Some interactor logic.
         */
        mPrefCallback.onResponse(mPreferences);
    }
}
