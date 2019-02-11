package com.example.oskin.lesson_15_clean_architecture.Data.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.ForecastDTOInput;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.SharedPrefDTO;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.Enums.Cities;

public class SettingsManager {
    private String PREF_SETTINGS = "SETTINGS";

    private String SETTINGS_CITY = "SETTINGS_CITY";
    private String SETTINGS_COUNT_DAYS = "SETTINGS_COUNT_DAYS";
    private String SETTINGS_TEMP_UNIT_IS_CELSIUS = "SETTINGS_TEMP_UNIT_IS_CELSIUS";
    private String SETTINGS_WIND_SPEED_UNIT_IS_KMH = "SETTINGS_WIND_SPEED_UNIT_IS_KMH";
    private String SETTINGS_PRECIP_UNIT_IS_MM = "SETTINGS_PRECIP_UNIT_IS_MM";

    private SharedPreferences mPreferences;
    private SharedPrefDTO mSharedPrefDTO;

    public SettingsManager(){
    }

    public ForecastDTOInput getForecastDTOInput(Context context){
        ForecastDTOInput dtoInput = new ForecastDTOInput();

        mPreferences = context.getSharedPreferences(PREF_SETTINGS, Context.MODE_PRIVATE);

        dtoInput.setCityName(mPreferences.getString(SETTINGS_CITY, Cities.Moscow.name()));
        dtoInput.setCountDays(mPreferences.getInt(SETTINGS_COUNT_DAYS, 7));

        return dtoInput;
    }

    public SharedPrefDTO getSharedPrefInDTO(Context context){
        mSharedPrefDTO = new SharedPrefDTO();

        mPreferences = context.getSharedPreferences(PREF_SETTINGS, Context.MODE_PRIVATE);

        mSharedPrefDTO.setCityName(mPreferences.getString(SETTINGS_CITY, Cities.Moscow.name()));
        mSharedPrefDTO.setCountDays(mPreferences.getInt(SETTINGS_COUNT_DAYS, 7));
        mSharedPrefDTO.setCelsius(mPreferences.getBoolean(SETTINGS_TEMP_UNIT_IS_CELSIUS,true));
        mSharedPrefDTO.setKm(mPreferences.getBoolean(SETTINGS_WIND_SPEED_UNIT_IS_KMH,true));
        mSharedPrefDTO.setMmHg(mPreferences.getBoolean(SETTINGS_PRECIP_UNIT_IS_MM,true));

        return mSharedPrefDTO;
    }

    public void loadSharedPref(Context context, SharedPrefDTO sharedPrefDTO){
        mPreferences = context.getSharedPreferences(SETTINGS_CITY, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = mPreferences.edit();

        editor.putString(SETTINGS_CITY, sharedPrefDTO.getCityName());
        editor.putInt(SETTINGS_COUNT_DAYS, sharedPrefDTO.getCountDays());
        editor.putBoolean(SETTINGS_TEMP_UNIT_IS_CELSIUS, sharedPrefDTO.isCelsius());
        editor.putBoolean(SETTINGS_PRECIP_UNIT_IS_MM, sharedPrefDTO.isMmHg());
        editor.putBoolean(SETTINGS_WIND_SPEED_UNIT_IS_KMH, sharedPrefDTO.isKm());
        editor.apply();

    }


}
