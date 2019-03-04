package com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.presenters;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.Forecast;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.ResponseBundle;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.UserPreferences;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.SelectedDayInteractor;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.UserPreferencesInteractor;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class DayPresenter {

    private IDayView mView;
    private UserPreferences mUserPreferences;
    private Forecast.Day mDay;
    private SelectedDayInteractor mSelectedDayInteractor;
    private UserPreferencesInteractor mUserPreferencesInteractor;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public DayPresenter(SelectedDayInteractor selectedDayInteractor,
                        UserPreferencesInteractor userPreferencesInteractor) {
        mSelectedDayInteractor = selectedDayInteractor;
        mUserPreferencesInteractor = userPreferencesInteractor;
    }

    public void onAttach(IDayView view) {
        mView = view;
        getUserPref();
    }

    public void onDetach() {
        mView = null;
        mCompositeDisposable.clear();
    }

    public void onDestroy() {
        mCompositeDisposable.dispose();
    }

    private void getUserPref() {
        mCompositeDisposable.add(mUserPreferencesInteractor.getUserPref()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::settingsResponse));
    }

    private void settingsResponse(ResponseBundle<UserPreferences> bundle) {
        if (bundle.isHasValue()) {
            mUserPreferences = bundle.getResponse();
            getSelectedDay();
        }
    }

    private void getSelectedDay() {
        mCompositeDisposable.add(mSelectedDayInteractor.getSelectedDay()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::selectedDayResponse));
    }

    private void selectedDayResponse(Forecast.Day day) {
        mDay = day;
        setData();
    }

    private void setData() {
        if (mView == null) {
            return;
        }
        mView.setSharedPrefDto(mUserPreferences);
        mView.setSelectedDay(mDay);
        mView.displayData();
    }
}
