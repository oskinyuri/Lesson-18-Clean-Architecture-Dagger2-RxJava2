package com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.interfaces.dip.ISettingsRepository;

//TODO delete it
public class SetUserPreferencesInteractor {

    private ISettingsRepository mRepository;

    public SetUserPreferencesInteractor(ISettingsRepository repository) {
        mRepository = repository;
    }
}
