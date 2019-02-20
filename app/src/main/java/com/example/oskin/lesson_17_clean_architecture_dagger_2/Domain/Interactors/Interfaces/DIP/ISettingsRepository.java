package com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.DIP;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.UserPreferences;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.Callbacks.GetUserPrefCallback;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.Callbacks.SetUserPrefCallback;

public interface ISettingsRepository {

    void getUserPreferences(GetUserPrefCallback callback);

    void setUserPreferences(UserPreferences dtoOutput, SetUserPrefCallback callback);

}
