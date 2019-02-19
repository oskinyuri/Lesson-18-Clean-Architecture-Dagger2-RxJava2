package com.example.oskin.lesson_15_clean_architecture.Data.SharedPreferences.WeatherPreferences;

import android.content.Context;

import com.example.oskin.lesson_15_clean_architecture.Data.Entity.DTO.LastRequestInfo;
import com.example.oskin.lesson_15_clean_architecture.Data.Repositories.WeatherRepository.ISharedPrefManager;
import com.example.oskin.lesson_15_clean_architecture.Data.SharedPreferences.SettingsPreferences.UserSettingsManager;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.ForecastDTOOutput;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.UserPreferences;

public class SharedPrefManager implements ISharedPrefManager {
    private UserSettingsManager mUserSettingsManager;
    private LastRequestManager mLastRequestManager;
    private SelectedDayManager mSelectedDayManager;
    private Context mContext;

    public SharedPrefManager(Context context) {
        mContext = context;
        mUserSettingsManager = new UserSettingsManager(context);
        mLastRequestManager = new LastRequestManager();
        mSelectedDayManager = new SelectedDayManager();
    }

    @Override
    public UserPreferences getSharedPrefInDTO() {
        return mUserSettingsManager.getUserPreferences();
    }

    @Override
    public ForecastDTOOutput.Day getSelectedDay() {
        return mSelectedDayManager.getSelectedDay(mContext);
    }

    @Override
    public void setSelectedDay(ForecastDTOOutput.Day day) {
        mSelectedDayManager.setSelectedDay(day, mContext);
    }

    @Override
    public void setLastRequest(LastRequestInfo info) {
        mLastRequestManager.setLastRequest(info, mContext);
    }

    @Override
    public LastRequestInfo getLastRequest() {
        return mLastRequestManager.getLastRequest(mContext);
    }
}
