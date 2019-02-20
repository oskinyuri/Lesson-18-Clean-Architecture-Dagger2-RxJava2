package com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.Repositories.SettingsRepository;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.SharedPreferences.SettingsPreferences.UserSettingsManager;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.UserPreferences;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.Callbacks.GetUserPrefCallback;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.DIP.ISettingsRepository;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.Callbacks.SetUserPrefCallback;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SettingRepository implements ISettingsRepository {

    private Context mContext;
    private IUserSettingManager mSettingManager;

    public SettingRepository(Context context, UserSettingsManager userSettingsManager) {
        mContext = context;
        mSettingManager = userSettingsManager;
    }

    @Override
    public void getUserPreferences(GetUserPrefCallback callback) {
        callback.onResponse(mSettingManager.getUserPreferences());
    }

    @Override
    public void setUserPreferences(UserPreferences userPreferences, SetUserPrefCallback callback) {

        Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
        try {
            List<Address> addreses = geocoder.getFromLocationName(userPreferences.getCityName(), 1);
            if (addreses.size() == 0){
                callback.onFailure();
                return;
            }
            Address address = addreses.get(0);
            userPreferences.setLatitude((float) address.getLatitude());
            userPreferences.setLongitude((float) address.getLongitude());
        } catch (IOException e) {
            e.printStackTrace();
            callback.onFailure();
        }

        mSettingManager.setUserPreferences(userPreferences);
        callback.onResponse();
    }
}
