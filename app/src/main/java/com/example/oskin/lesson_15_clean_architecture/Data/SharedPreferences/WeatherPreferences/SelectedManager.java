package com.example.oskin.lesson_15_clean_architecture.Data.SharedPreferences.WeatherPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.ForecastDTOOutput;
import com.google.gson.Gson;

public class SelectedManager {

    private String PREF_SELECTED_DAY = "PREF_SELECTED_DAY";
    private String SELECTED_DAY = "SELECTED_DAY";

    private SharedPreferences mSharedPreferences;

    public void setSelectedDay(ForecastDTOOutput.Day day, Context context){
        mSharedPreferences = context.getSharedPreferences(PREF_SELECTED_DAY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(day);
        editor.putString(SELECTED_DAY, json);
        editor.apply();
    }

    public ForecastDTOOutput.Day getSelectedDay(Context context){
        mSharedPreferences = context.getSharedPreferences(PREF_SELECTED_DAY, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mSharedPreferences.getString(SELECTED_DAY, "");
        ForecastDTOOutput.Day day = gson.fromJson(json, ForecastDTOOutput.Day.class);
        return day;
    }
}
