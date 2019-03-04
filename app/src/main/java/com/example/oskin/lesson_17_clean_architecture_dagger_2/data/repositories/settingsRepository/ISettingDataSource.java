package com.example.oskin.lesson_17_clean_architecture_dagger_2.data.repositories.settingsRepository;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.UserPreferences;

public interface ISettingDataSource {
    void setUserPreferences(UserPreferences userPreferences);
    UserPreferences getUserPreferences();
}
