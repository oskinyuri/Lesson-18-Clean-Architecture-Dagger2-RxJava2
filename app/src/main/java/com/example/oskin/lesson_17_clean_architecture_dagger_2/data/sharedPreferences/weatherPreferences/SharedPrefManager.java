package com.example.oskin.lesson_17_clean_architecture_dagger_2.data.sharedPreferences.weatherPreferences;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.data.entity.DTO.LastRequestInfo;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.data.repositories.weatherRepository.ISharedPrefManager;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.data.sharedPreferences.settingsPreferences.SettingsDataSource;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.Forecast;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.UserPreferences;

public class SharedPrefManager implements ISharedPrefManager {
    private SettingsDataSource mUserSettingsManager;
    private LastRequestManager mLastRequestManager;
    private SelectedDayManager mSelectedDayManager;

    public SharedPrefManager(SettingsDataSource userSettingsManager,
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
    public Forecast.Day getSelectedDay() {
        return mSelectedDayManager.getSelectedDay();
    }

    @Override
    public void setSelectedDay(Forecast.Day day) {
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
