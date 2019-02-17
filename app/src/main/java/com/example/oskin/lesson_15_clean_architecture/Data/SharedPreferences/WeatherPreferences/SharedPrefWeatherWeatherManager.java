package com.example.oskin.lesson_15_clean_architecture.Data.SharedPreferences.WeatherPreferences;

import android.content.Context;

import com.example.oskin.lesson_15_clean_architecture.Data.Entity.DTO.LastRequestInfo;
import com.example.oskin.lesson_15_clean_architecture.Data.Repositories.WeatherRepository.ISharedPrefWeatherManager;
import com.example.oskin.lesson_15_clean_architecture.Data.SharedPreferences.SettingsPreferences.SharedPrefSettingManager;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.ForecastDTOOutput;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.SharedPrefDTO;

public class SharedPrefWeatherWeatherManager implements ISharedPrefWeatherManager {
    private SharedPrefSettingManager mSharedPrefSettingManager;
    private LastRequestManager mLastRequestManager;
    private SelectedManager mSelectedManager;
    private Context mContext;

    public SharedPrefWeatherWeatherManager(Context context) {
        mContext = context;
        mSharedPrefSettingManager = new SharedPrefSettingManager(context);
        mLastRequestManager = new LastRequestManager();
        mSelectedManager = new SelectedManager();
    }

    @Override
    public SharedPrefDTO getSharedPrefInDTO() {
        return mSharedPrefSettingManager.getSharedPrefInDTO();
    }

    @Override
    public ForecastDTOOutput.Day getSelectedDay() {
        return mSelectedManager.getSelectedDay(mContext);
    }

    @Override
    public void setSelectedDay(ForecastDTOOutput.Day day) {
        mSelectedManager.setSelectedDay(day, mContext);
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
