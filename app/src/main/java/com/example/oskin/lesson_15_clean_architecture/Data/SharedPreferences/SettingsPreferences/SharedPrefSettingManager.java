package com.example.oskin.lesson_15_clean_architecture.Data.SharedPreferences.SettingsPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.oskin.lesson_15_clean_architecture.Data.Repositories.SettingsRepository.ISharedPrefSettingsRepository;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.SharedPrefDTO;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.Enums.Cities;

public class SharedPrefSettingManager implements ISharedPrefSettingsRepository {

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
    private SharedPrefDTO mSharedPrefDTO;

    public SharedPrefSettingManager(Context context){
        mContext = context;
    }

    @Override
    public SharedPrefDTO getSharedPrefInDTO(){
        mSharedPrefDTO = new SharedPrefDTO();

        mPreferences = mContext.getSharedPreferences(PREF_SETTINGS, Context.MODE_PRIVATE);

        mSharedPrefDTO.setCityName(mPreferences.getString(SETTINGS_CITY, Cities.Moscow.name()));
        mSharedPrefDTO.setLatitude(mPreferences.getFloat(SETTINGS_LATITUDE, 55.7522f));
        mSharedPrefDTO.setLongitude(mPreferences.getFloat(SETTINGS_LONGITUDE, 37.6155f));
        mSharedPrefDTO.setCountDays(mPreferences.getInt(SETTINGS_COUNT_DAYS, 7));
        mSharedPrefDTO.setCelsius(mPreferences.getBoolean(SETTINGS_TEMP_UNIT_IS_CELSIUS,true));
        mSharedPrefDTO.setKm(mPreferences.getBoolean(SETTINGS_WIND_SPEED_UNIT_IS_KMH,true));
        mSharedPrefDTO.setMm(mPreferences.getBoolean(SETTINGS_PRECIP_UNIT_IS_MM,true));

        return mSharedPrefDTO;
    }

    @Override
    public void loadSharedPref(SharedPrefDTO sharedPrefDTO){
        mPreferences = mContext.getSharedPreferences(PREF_SETTINGS, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = mPreferences.edit();

        editor.putString(SETTINGS_CITY, sharedPrefDTO.getCityName());
        editor.putFloat(SETTINGS_LATITUDE, sharedPrefDTO.getLatitude());
        editor.putFloat(SETTINGS_LONGITUDE, sharedPrefDTO.getLongitude());
        editor.putInt(SETTINGS_COUNT_DAYS, sharedPrefDTO.getCountDays());
        editor.putBoolean(SETTINGS_TEMP_UNIT_IS_CELSIUS, sharedPrefDTO.isCelsius());
        editor.putBoolean(SETTINGS_PRECIP_UNIT_IS_MM, sharedPrefDTO.isMm());
        editor.putBoolean(SETTINGS_WIND_SPEED_UNIT_IS_KMH, sharedPrefDTO.isKm());
        editor.commit();
    }


}
