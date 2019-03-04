package com.example.oskin.lesson_17_clean_architecture_dagger_2.data.repositories.settingsRepository;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.data.sharedPreferences.settingsPreferences.SettingsDataSource;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.ResponseBundle;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.UserPreferences;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.interfaces.dip.ISettingsRepository;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import io.reactivex.Completable;
import io.reactivex.Single;

public class SettingRepository implements ISettingsRepository {

    private Context mContext;
    private ISettingDataSource mDataSource;

    private UserPreferences mUserPreferences;

    public SettingRepository(Context context, SettingsDataSource userSettingsManager) {
        mContext = context;
        mDataSource = userSettingsManager;
    }

    public Single<ResponseBundle<UserPreferences>> getUserPref() {
        return Single.fromCallable(() -> new ResponseBundle<>(mDataSource.getUserPreferences()));
    }

    @Override
    public Completable setUserPref(UserPreferences userPreferences) {
        return Completable.fromAction(() -> setToSource(userPreferences));
    }

    private void setToSource(UserPreferences userPreferences) throws IOException {
        mUserPreferences = mDataSource.getUserPreferences();

        if (!(mUserPreferences.getCityName().equals(userPreferences.getCityName()))) {
            Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
            try {
                List<Address> addreses = geocoder.getFromLocationName(userPreferences.getCityName(), 1);
                if (addreses.size() == 0) {
                    throw new IOException("Not find city.");
                }
                Address address = addreses.get(0);
                userPreferences.setLatitude((float) address.getLatitude());
                userPreferences.setLongitude((float) address.getLongitude());
            } catch (IOException e) {
                throw new IOException("No internet connection");
            }
        }
        mDataSource.setUserPreferences(userPreferences);
    }
}
