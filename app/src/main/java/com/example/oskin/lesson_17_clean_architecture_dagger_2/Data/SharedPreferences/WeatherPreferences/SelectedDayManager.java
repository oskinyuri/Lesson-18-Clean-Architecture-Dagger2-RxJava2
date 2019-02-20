package com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.SharedPreferences.WeatherPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.ForecastDTOOutput;
import com.google.gson.Gson;

import javax.inject.Inject;

public class SelectedDayManager {

    private String PREF_SELECTED_DAY = "PREF_SELECTED_DAY";
    private String SELECTED_DAY = "SELECTED_DAY";

    private SharedPreferences mSharedPreferences;

    private Context mContext;
    private Gson mGson;

    @Inject
    public SelectedDayManager(Context context, Gson gson) {
        mContext = context;
        mGson = gson;
    }

    public void setSelectedDay(ForecastDTOOutput.Day day) {
        mSharedPreferences = mContext.getSharedPreferences(PREF_SELECTED_DAY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        String json = mGson.toJson(day);
        editor.putString(SELECTED_DAY, json);
        editor.apply();
    }

    public ForecastDTOOutput.Day getSelectedDay() {
        mSharedPreferences = mContext.getSharedPreferences(PREF_SELECTED_DAY, Context.MODE_PRIVATE);
        String json = mSharedPreferences.getString(SELECTED_DAY, "");
        ForecastDTOOutput.Day day = mGson.fromJson(json, ForecastDTOOutput.Day.class);
        return day;
    }
}
