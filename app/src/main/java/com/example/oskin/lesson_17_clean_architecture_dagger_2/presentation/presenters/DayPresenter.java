package com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.presenters;

import android.os.Handler;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.Forecast;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.ResponseBundle;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.UserPreferences;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.GetSelectedDayInteractor;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.UserPreferencesInteractor;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.interfaces.callbacks.GetSelectedDayCallback;

import java.util.concurrent.ExecutorService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DayPresenter {

    private IDayView mView;
    private UserPreferences mUserPreferences;
    private Forecast.Day mDay;
    private GetSelectedDayInteractor mGetSelectedDayInteractor;
    private UserPreferencesInteractor mUserPreferencesInteractor;
    private ExecutorService mExecutorService;
    private final Handler mHandler;
    private Disposable mGetDisposable;

    public DayPresenter(ExecutorService executorService,
                        GetSelectedDayInteractor getSelectedDayInteractor,
                        UserPreferencesInteractor userPreferencesInteractor,
                        Handler handler) {

        mExecutorService = executorService;
        mGetSelectedDayInteractor = getSelectedDayInteractor;
        mUserPreferencesInteractor = userPreferencesInteractor;
        mHandler = handler;
    }

    private void initObservable(){
        mGetDisposable = mUserPreferencesInteractor.getUserPref()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::getResponse);
    }

    public void onAttach(IDayView view) {
        mView = view;

    }

    public void onDetach() {
        mView = null;
        mGetDisposable.dispose();
    }

    public void getDay() {
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                mGetSelectedDayInteractor.getSelectedDay(new GetSelectedDayCallback() {
                    @Override
                    public void onResponse(Forecast.Day day) {
                        mDay = day;
                        initObservable();

                    }
                });
            }
        });
    }

    private void getResponse(ResponseBundle<UserPreferences> bundle) {
        if (bundle.isHasValue())
            mUserPreferences = bundle.getResponse();
        setData();
    }

    private void setData() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mView == null) {
                    return;
                }
                mView.setSharedPrefDto(mUserPreferences);
                mView.setSelectedDay(mDay);
                mView.displayData();
            }
        });
    }
}
