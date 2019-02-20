package com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.SharedPreferences.WeatherPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.Entity.DTO.LastRequestInfo;

import javax.inject.Inject;

public class LastRequestManager {

    private String PREF_LAST_REQUEST = "PREF_LAST_REQUEST";
    private String LAST_LOAD_TIME = "LAST_LOAD_TIME";
    private String LAST_LOAD_CITY_NAME = "LAST_LOAD_CITY_NAME";
    private String LAST_LOAD_COUNT_DAYS = "LAST_LOAD_COUNT_DAYS";

    private Context mContext;

    private SharedPreferences mPreferences;

    @Inject
    public LastRequestManager(Context context) {
        mContext = context;
    }

    /**
     * Сохранение информации о последнем запросе.
     *
     * @param info    - информация о последнем запросе.
     */
    public void setLastRequest(LastRequestInfo info) {

        mPreferences = mContext.getSharedPreferences(PREF_LAST_REQUEST, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putLong(LAST_LOAD_TIME, info.getLastTimeInEpoch());
        editor.putString(LAST_LOAD_CITY_NAME, info.getLastCityName());
        editor.putInt(LAST_LOAD_COUNT_DAYS, info.getLastCountDays());
        editor.apply();
    }

    /**
     * Возвращает информацию о последнем запросе в удобочитаемом для репозитория виде.
     *
     * @return объект последнего запроса.
     */
    public LastRequestInfo getLastRequest() {

        LastRequestInfo info = new LastRequestInfo();
        mPreferences = mContext.getSharedPreferences(PREF_LAST_REQUEST, Context.MODE_PRIVATE);
        info.setLastCityName(mPreferences.getString(LAST_LOAD_CITY_NAME, ""));
        info.setLastTimeInEpoch(mPreferences.getLong(LAST_LOAD_TIME, 0));
        info.setLastCountDays(mPreferences.getInt(LAST_LOAD_COUNT_DAYS, 7));
        return info;
    }

}
