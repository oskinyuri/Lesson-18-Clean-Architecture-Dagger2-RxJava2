package com.example.oskin.lesson_15_clean_architecture.Presentation.Presenters;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.UserPreferences;

public interface ISettingView {

    void setUserPref(UserPreferences userPreferences);
    void makeToast(String text);
    void setSpinnerAdapter(Integer[] data);

}
