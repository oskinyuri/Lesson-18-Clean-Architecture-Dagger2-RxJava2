package com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.Presenters;

import android.os.Handler;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.Forecast;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.UserPreferences;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.GetSelectedDayInteractor;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.GetUserPreferencesInteractor;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.Callbacks.GetSelectedDayCallback;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.Callbacks.GetUserPrefCallback;

import java.util.concurrent.ExecutorService;

public class DayPresenter {

    private IDayView mView;
    private UserPreferences mPrefDTO;
    private Forecast.Day mDay;
    private GetSelectedDayInteractor mGetSelectedDayInteractor;
    private GetUserPreferencesInteractor mGetUserPreferencesInteractor;
    private ExecutorService mExecutorService;
    private final Handler mHandler;

    public DayPresenter(ExecutorService executorService,
                        GetSelectedDayInteractor getSelectedDayInteractor,
                        GetUserPreferencesInteractor getUserPreferencesInteractor,
                        Handler handler) {

        mExecutorService = executorService;
        mGetSelectedDayInteractor = getSelectedDayInteractor;
        mGetUserPreferencesInteractor = getUserPreferencesInteractor;
        mHandler = handler;
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
                    public void onResponse(Forecast.Day day) {
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
