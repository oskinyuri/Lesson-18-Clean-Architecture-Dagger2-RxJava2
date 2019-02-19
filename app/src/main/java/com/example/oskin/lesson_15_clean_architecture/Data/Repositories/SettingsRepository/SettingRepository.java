package com.example.oskin.lesson_15_clean_architecture.Data.Repositories.SettingsRepository;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.example.oskin.lesson_15_clean_architecture.Data.SharedPreferences.SettingsPreferences.UserSettingsManager;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.UserPreferences;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.Callbacks.GetUserPrefCallback;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.DIP.ISettingsRepository;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.Callbacks.SetUserPrefCallback;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SettingRepository implements ISettingsRepository {

    private Context mContext;
    private IUserSettingManager mPrefManager;

    public SettingRepository(Context context) {
        mContext = context;
        mPrefManager = new UserSettingsManager(mContext);
    }

    @Override
    public void getUserPreferences(GetUserPrefCallback callback) {
        callback.onResponse(mPrefManager.getUserPreferences());
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

        mPrefManager.setUserPreferences(userPreferences);
        callback.onResponse();
    }
}
