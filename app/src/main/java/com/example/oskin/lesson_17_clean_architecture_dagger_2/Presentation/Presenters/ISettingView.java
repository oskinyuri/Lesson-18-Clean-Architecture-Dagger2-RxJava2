package com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.Presenters;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.UserPreferences;

public interface ISettingView {

    void setUserPref(UserPreferences userPreferences);
    void makeToast(String text);
    void setSpinnerAdapter(Integer[] data);

}
