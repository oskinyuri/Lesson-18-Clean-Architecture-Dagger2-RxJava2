package com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.Repositories.SettingsRepository;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.UserPreferences;

public interface IUserSettingManager {
    void setUserPreferences(UserPreferences userPreferences);
    UserPreferences getUserPreferences();
}
