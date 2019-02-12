package com.example.oskin.lesson_15_clean_architecture.Data.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class LastTimeLoadManager {

    private String PREF_LAST_LOAD_TIME = "PREF_LAST_LOAD_TIME";
    private String LAST_LOAD_TIME = "LAST_LOAD_TIME";

    private SharedPreferences mPreferences;

    public void setLastTimeLoad(long timeInEpoch, Context context){

        mPreferences = context.getSharedPreferences(PREF_LAST_LOAD_TIME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putLong(LAST_LOAD_TIME, timeInEpoch);
        editor.apply();

    }

    public long getLastTimeLoad(Context context){
        mPreferences = context.getSharedPreferences(PREF_LAST_LOAD_TIME, Context.MODE_PRIVATE);
        return mPreferences.getLong(LAST_LOAD_TIME, 0);
    }

}
