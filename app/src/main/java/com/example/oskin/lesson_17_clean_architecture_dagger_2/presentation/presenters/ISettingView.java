package com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.presenters;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.UserPreferences;

public interface ISettingView {

    void setUserPref(UserPreferences userPreferences);
    void makeToast(String text);
    void setSpinnerAdapter(Integer[] data);

}
