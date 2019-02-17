package com.example.oskin.lesson_15_clean_architecture.Presentation.Presenters;

import android.os.Handler;
import android.os.Looper;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.ForecastDTOOutput;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.SharedPrefDTO;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.GetSelectedDayInteractor;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.GetSharedPrefSettingsInteractor;
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
    private GetSelectedDayInteractor mGetSelectedDayInteractor;
    private GetSharedPrefSettingsInteractor mGetSharedPrefSettingsInteractor;
    private ExecutorService mExecutorService;

    private final Handler mHandler = new Handler(Looper.getMainLooper());

    public DayPresenter() {
        mExecutorService = Executors.newSingleThreadExecutor();
        mSettingsRepository = WeatherApp.getSettingRepository();
        mWeatherRepository = WeatherApp.getWeatherRepository();
        mGetSelectedDayInteractor = new GetSelectedDayInteractor(mWeatherRepository);
        mGetSharedPrefSettingsInteractor = new GetSharedPrefSettingsInteractor(mSettingsRepository);
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

                mGetSharedPrefSettingsInteractor.getSharedPref(new GetSharedPrefCallback() {
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
