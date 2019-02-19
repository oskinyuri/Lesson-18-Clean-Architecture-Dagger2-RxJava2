package com.example.oskin.lesson_15_clean_architecture.Data.Repositories.SettingsRepository;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.UserPreferences;

public interface IUserSettingManager {
    void setUserPreferences(UserPreferences userPreferences);
    UserPreferences getUserPreferences();
}
