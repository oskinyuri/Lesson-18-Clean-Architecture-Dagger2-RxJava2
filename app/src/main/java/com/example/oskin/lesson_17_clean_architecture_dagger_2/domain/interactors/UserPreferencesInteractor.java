package com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.ResponseBundle;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.UserPreferences;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.interfaces.callbacks.GetUserPrefCallback;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.interfaces.dip.ISettingsRepository;

import io.reactivex.Completable;
import io.reactivex.Single;

public class UserPreferencesInteractor {

    private ISettingsRepository mRepository;

    public UserPreferencesInteractor(ISettingsRepository repository) {
        mRepository = repository;
    }

    public Completable setUserPref(UserPreferences userPreferences){
        return mRepository.setUserPref(userPreferences);
    }

    public Single<ResponseBundle<UserPreferences>> getUserPref(){
        return mRepository.getUserPref();
    }
}
