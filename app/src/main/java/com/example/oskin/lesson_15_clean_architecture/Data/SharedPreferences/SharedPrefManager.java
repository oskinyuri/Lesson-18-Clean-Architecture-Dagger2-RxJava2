package com.example.oskin.lesson_15_clean_architecture.Data.SharedPreferences;

import android.content.Context;

import com.example.oskin.lesson_15_clean_architecture.Data.Repositories.ISharedPrefManager;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.ForecastDTOInput;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.ForecastDTOOutput;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.SharedPrefDTO;

public class SharedPrefManager implements ISharedPrefManager {
    private SettingsManager mSettingsManager;
    private LastTimeLoadManager mLastTimeLoadManager;
    private SelectedManager mSelectedManager;
    private Context mContext;

    public SharedPrefManager(Context context) {
        mContext = context;
        mSettingsManager = new SettingsManager();
        mLastTimeLoadManager = new LastTimeLoadManager();
        mSelectedManager = new SelectedManager();

    }

    @Override
    public void loadSharedPref(SharedPrefDTO sharedPrefDTO) {
        mSettingsManager.loadSharedPref(mContext, sharedPrefDTO);
    }

    @Override
    public SharedPrefDTO getSharedPrefInDTO() {
        return mSettingsManager.getSharedPrefInDTO(mContext);
    }

    @Override
    public void setLastTimeLoadInEpoch(long timeInEpoch) {
        mLastTimeLoadManager.setLastTimeLoad(timeInEpoch, mContext);
    }

    @Override
    public long getLastTimeLoadInEpoch() {
        return mLastTimeLoadManager.getLastTimeLoad(mContext);
    }

    @Override
    public ForecastDTOInput getForecastDTOInput() {
        return mSettingsManager.getForecastDTOInput(mContext);
    }

    @Override
    public ForecastDTOOutput.Day getSelectedDay() {
        return mSelectedManager.getSelectedDay(mContext);
    }

    @Override
    public void setSelectedDay(ForecastDTOOutput.Day day) {
        mSelectedManager.setSelectedDay(day, mContext);
    }
}
