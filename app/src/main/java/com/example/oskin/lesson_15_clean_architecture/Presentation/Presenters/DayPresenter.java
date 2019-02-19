package com.example.oskin.lesson_15_clean_architecture.Presentation.Presenters;

import android.os.Handler;
import android.os.Looper;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.ForecastDTOOutput;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.UserPreferences;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.GetSelectedDayInteractor;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.GetUserPreferencesInteractor;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.Callbacks.GetSelectedDayCallback;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.Callbacks.GetUserPrefCallback;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.DIP.ISettingsRepository;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.DIP.IWeatherRepository;
import com.example.oskin.lesson_15_clean_architecture.WeatherApp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DayPresenter {

    private IDayView mView;
    private UserPreferences mPrefDTO;
    private ForecastDTOOutput.Day mDay;
    private IWeatherRepository mWeatherRepository;
    private ISettingsRepository mSettingsRepository;
    private GetSelectedDayInteractor mGetSelectedDayInteractor;
    private GetUserPreferencesInteractor mGetUserPreferencesInteractor;
    private ExecutorService mExecutorService;

    private final Handler mHandler = new Handler(Looper.getMainLooper());

    public DayPresenter() {
        mExecutorService = Executors.newSingleThreadExecutor();
        mSettingsRepository = WeatherApp.getSettingRepository();
        mWeatherRepository = WeatherApp.getWeatherRepository();
        mGetSelectedDayInteractor = new GetSelectedDayInteractor(mWeatherRepository);
        mGetUserPreferencesInteractor = new GetUserPreferencesInteractor(mSettingsRepository);
    }

    public void onAttach(IDayView view) {
        mView = view;
    }

    public void onDetach() {
        mView = null;
    }

    public void getDay() {
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                mGetSelectedDayInteractor.getSelectedDay(new GetSelectedDayCallback() {
                    @Override
                    public void onResponse(ForecastDTOOutput.Day day) {
                        mDay = day;
                    }
                });

                mGetUserPreferencesInteractor.getUserPref(new GetUserPrefCallback() {
                    @Override
                    public void onResponse(UserPreferences userPreferences) {
                        mPrefDTO = userPreferences;
                    }
                });
                setData();
            }
        });
    }

    private void setData() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mView == null) {
                    return;
                }
                mView.setSharedPrefDto(mPrefDTO);
                mView.setSelectedDay(mDay);
                mView.displayData();
            }
        });
    }
}
