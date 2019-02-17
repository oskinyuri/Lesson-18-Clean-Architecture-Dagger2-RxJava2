package com.example.oskin.lesson_15_clean_architecture.Presentation.Presenters;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.SharedPrefDTO;

public interface ISettingView {

    void setSettingsSharedPref(SharedPrefDTO sharedPrefDTO);
    void setCitiesDropList();
    void setCountDaysDropList();
    void makeToast(String text);
    void setSpinnerAdapter(Integer[] data);

}
