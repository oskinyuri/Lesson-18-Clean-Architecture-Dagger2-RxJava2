package com.example.oskin.lesson_15_clean_architecture.Presentation.Presenters;

import android.os.Handler;
import android.os.Looper;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.ForecastDTOOutput;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.SharedPrefDTO;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.GetSelectedDay;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.GetSharedPreferencesSettings;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.GetSelectedDayCallback;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.GetSharedPrefCallback;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.ISettingsRepository;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.IWeatherRepository;
import com.example.oskin.lesson_15_clean_architecture.WeatherApp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DayPresenter {

    private IDayView mView;
    private SharedPrefDTO mPrefDTO;
    private ForecastDTOOutput.Day mDay;
    private IWeatherRepository mWeatherRepository;
    private ISettingsRepository mSettingsRepository;
    private GetSelectedDay mGetSelectedDay;
    private GetSharedPreferencesSettings mGetSharedPreferencesSettings;
    private ExecutorService mExecutorService;

    private final Handler mHandler = new Handler(Looper.getMainLooper());

    public DayPresenter() {
        mExecutorService = Executors.newSingleThreadExecutor();
        mSettingsRepository = WeatherApp.getSettingRepository();
        mWeatherRepository = WeatherApp.getWeatherRepository();
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
                mGetSelectedDay = new GetSelectedDay(mWeatherRepository);
                mGetSelectedDay.getSelectedDay(new GetSelectedDayCallback() {
                    @Override
                    public void onResponse(ForecastDTOOutput.Day day) {
                        mDay = day;
                    }
                });

                mGetSharedPreferencesSettings = new GetSharedPreferencesSettings(mSettingsRepository);
                mGetSharedPreferencesSettings.getSharedPref(new GetSharedPrefCallback() {
                    @Override
                    public void onResponse(SharedPrefDTO sharedPrefDTO) {
                        mPrefDTO = sharedPrefDTO;
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
