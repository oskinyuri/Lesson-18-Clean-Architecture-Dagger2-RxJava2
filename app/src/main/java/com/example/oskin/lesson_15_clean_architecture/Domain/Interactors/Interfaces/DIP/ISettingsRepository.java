package com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.DIP;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.UserPreferences;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.Callbacks.GetUserPrefCallback;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.Callbacks.SetUserPrefCallback;

public interface ISettingsRepository {

    void getUserPreferences(GetUserPrefCallback callback);

    void setUserPreferences(UserPreferences dtoOutput, SetUserPrefCallback callback);

}
