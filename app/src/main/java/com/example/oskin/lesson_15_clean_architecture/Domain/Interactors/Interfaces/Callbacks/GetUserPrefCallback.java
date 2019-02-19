package com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.Callbacks;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.UserPreferences;

public interface GetUserPrefCallback {
    void onResponse(UserPreferences userPreferences);
}
