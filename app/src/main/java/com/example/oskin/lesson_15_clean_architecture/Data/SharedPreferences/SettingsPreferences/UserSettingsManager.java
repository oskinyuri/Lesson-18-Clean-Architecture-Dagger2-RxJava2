package com.example.oskin.lesson_15_clean_architecture.Data.SharedPreferences.SettingsPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.oskin.lesson_15_clean_architecture.Data.Repositories.SettingsRepository.IUserSettingManager;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.UserPreferences;

public class UserSettingsManager implements IUserSettingManager {

    private String PREF_SETTINGS = "SETTINGS";

    private String SETTINGS_CITY = "SETTINGS_CITY";
    private String SETTINGS_LATITUDE = "SETTINGS_LATITUDE";
    private String SETTINGS_LONGITUDE = "SETTINGS_LONGITUDE";
    private String SETTINGS_COUNT_DAYS = "SETTINGS_COUNT_DAYS";
    private String SETTINGS_TEMP_UNIT_IS_CELSIUS = "SETTINGS_TEMP_UNIT_IS_CELSIUS";
    private String SETTINGS_WIND_SPEED_UNIT_IS_KMH = "SETTINGS_WIND_SPEED_UNIT_IS_KMH";
    private String SETTINGS_PRECIP_UNIT_IS_MM = "SETTINGS_PRECIP_UNIT_IS_MM";

    private Context mContext;
    private SharedPreferences mPreferences;
    private UserPreferences mUserPreferences;

    public UserSettingsManager(Context context){
        mContext = context;
    }

    @Override
    public UserPreferences getUserPreferences(){
        mUserPreferences = new UserPreferences();

        mPreferences = mContext.getSharedPreferences(PREF_SETTINGS, Context.MODE_PRIVATE);

        //TODO Когда-нибудь добавить определение локации
        mUserPreferences.setCityName(mPreferences.getString(SETTINGS_CITY, "Moscow"));
        mUserPreferences.setLatitude(mPreferences.getFloat(SETTINGS_LATITUDE, 55.7522f));
        mUserPreferences.setLongitude(mPreferences.getFloat(SETTINGS_LONGITUDE, 37.6155f));
        mUserPreferences.setCountDays(mPreferences.getInt(SETTINGS_COUNT_DAYS, 7));
        mUserPreferences.setCelsius(mPreferences.getBoolean(SETTINGS_TEMP_UNIT_IS_CELSIUS,true));
        mUserPreferences.setKm(mPreferences.getBoolean(SETTINGS_WIND_SPEED_UNIT_IS_KMH,true));
        mUserPreferences.setMm(mPreferences.getBoolean(SETTINGS_PRECIP_UNIT_IS_MM,true));

        return mUserPreferences;
    }

    @Override
    public void setUserPreferences(UserPreferences userPreferences){
        mPreferences = mContext.getSharedPreferences(PREF_SETTINGS, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = mPreferences.edit();

        editor.putString(SETTINGS_CITY, userPreferences.getCityName());
        editor.putFloat(SETTINGS_LATITUDE, userPreferences.getLatitude());
        editor.putFloat(SETTINGS_LONGITUDE, userPreferences.getLongitude());
        editor.putInt(SETTINGS_COUNT_DAYS, userPreferences.getCountDays());
        editor.putBoolean(SETTINGS_TEMP_UNIT_IS_CELSIUS, userPreferences.isCelsius());
        editor.putBoolean(SETTINGS_PRECIP_UNIT_IS_MM, userPreferences.isMm());
        editor.putBoolean(SETTINGS_WIND_SPEED_UNIT_IS_KMH, userPreferences.isKm());
        editor.commit();
    }


}
