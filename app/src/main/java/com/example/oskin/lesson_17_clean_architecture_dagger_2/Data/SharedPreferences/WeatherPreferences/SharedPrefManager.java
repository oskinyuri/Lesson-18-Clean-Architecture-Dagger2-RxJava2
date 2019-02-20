package com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.SharedPreferences.WeatherPreferences;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.Entity.DTO.LastRequestInfo;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.Repositories.WeatherRepository.ISharedPrefManager;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.SharedPreferences.SettingsPreferences.UserSettingsManager;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.ForecastDTOOutput;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.UserPreferences;

public class SharedPrefManager implements ISharedPrefManager {
    private UserSettingsManager mUserSettingsManager;
    private LastRequestManager mLastRequestManager;
    private SelectedDayManager mSelectedDayManager;

    public SharedPrefManager(UserSettingsManager userSettingsManager,
                             LastRequestManager lastRequestManager,
                             SelectedDayManager selectedDayManager) {
        mUserSettingsManager = userSettingsManager;
        mLastRequestManager = lastRequestManager;
        mSelectedDayManager = selectedDayManager;
    }

    @Override
    public UserPreferences getSharedPrefInDTO() {
        return mUserSettingsManager.getUserPreferences();
    }

    @Override
    public ForecastDTOOutput.Day getSelectedDay() {
        return mSelectedDayManager.getSelectedDay();
    }

    @Override
    public void setSelectedDay(ForecastDTOOutput.Day day) {
        mSelectedDayManager.setSelectedDay(day);
    }

    @Override
    public void setLastRequest(LastRequestInfo info) {
        mLastRequestManager.setLastRequest(info);
    }

    @Override
    public LastRequestInfo getLastRequest() {
        return mLastRequestManager.getLastRequest();
    }
}
