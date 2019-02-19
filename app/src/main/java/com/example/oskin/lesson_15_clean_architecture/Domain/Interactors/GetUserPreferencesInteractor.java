package com.example.oskin.lesson_15_clean_architecture.Domain.Interactors;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.UserPreferences;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.Callbacks.GetUserPrefCallback;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.DIP.ISettingsRepository;

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
