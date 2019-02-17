package com.example.oskin.lesson_15_clean_architecture.Data.Repositories.SettingsRepository;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.example.oskin.lesson_15_clean_architecture.Data.SharedPreferences.SettingsPreferences.SharedPrefSettingManager;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.SharedPrefDTO;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.GetSharedPrefCallback;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.ISettingsRepository;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.LoadSharedPrefCallback;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SettingRepository implements ISettingsRepository {

    private Context mContext;
    private ISharedPrefSettingsRepository mPrefManager;

    public SettingRepository(Context context) {
        mContext = context;
        mPrefManager = new SharedPrefSettingManager(mContext);
    }

    @Override
    public void getSharedPreferences(GetSharedPrefCallback callback) {
        callback.onResponse(mPrefManager.getSharedPrefInDTO());
    }

    @Override
    public void loadSharedPreferences(SharedPrefDTO sharedPrefDTO, LoadSharedPrefCallback callback) {

        Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
        try {
            List<Address> addreses = geocoder.getFromLocationName(sharedPrefDTO.getCityName(), 1);
            if (addreses.size() == 0){
                callback.onFailure();
                return;
            }
            Address address = addreses.get(0);
            sharedPrefDTO.setLatitude((float) address.getLatitude());
            sharedPrefDTO.setLongitude((float) address.getLongitude());
        } catch (IOException e) {
            e.printStackTrace();
            callback.onFailure();
        }

        mPrefManager.loadSharedPref(sharedPrefDTO);
        callback.onResponse();
    }
}
