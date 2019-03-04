package com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.interfaces.callbacks;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.UserPreferences;

public interface GetUserPrefCallback {
    void onResponse(UserPreferences userPreferences);
}
