package com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.interfaces.dip;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.ResponseBundle;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.UserPreferences;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface ISettingsRepository {

    Single<ResponseBundle<UserPreferences>> getUserPref();

    Completable setUserPref(UserPreferences userPreferences);

}
