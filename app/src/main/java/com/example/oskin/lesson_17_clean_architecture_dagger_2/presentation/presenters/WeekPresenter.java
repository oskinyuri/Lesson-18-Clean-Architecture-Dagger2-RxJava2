package com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.presenters;

import android.os.Handler;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.Forecast;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.ResponseBundle;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.UserPreferences;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.GetForecastInteractor;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.SetSelectedDayInteractor;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.UserPreferencesInteractor;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Qualifier.SingleThread;

import java.util.concurrent.ExecutorService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WeekPresenter {

    private IMainWeekView mView;

    private Forecast mForecast;

    private GetForecastInteractor mGetForecastInteractor;
    private SetSelectedDayInteractor mSetSelectedDayInteractor;
    private UserPreferencesInteractor mUserPreferencesInteractor;

    private UserPreferences mUserPreferences;

    //TODO inject from dagger
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    private Disposable getForecastDisposable;

    // True at first attach or if user settings was updated when view was detached
    private boolean needUpdate = true;

    public WeekPresenter(@SingleThread ExecutorService executorService,
                         Handler uiHandler,
                         GetForecastInteractor getForecastInteractor,
                         SetSelectedDayInteractor setSelectedDayInteractor,
                         UserPreferencesInteractor userPreferencesInteractor) {

        mGetForecastInteractor = getForecastInteractor;
        mSetSelectedDayInteractor = setSelectedDayInteractor;
        mUserPreferencesInteractor = userPreferencesInteractor;
    }

    public void onAttach(IMainWeekView view) {
        mView = view;

        mCompositeDisposable.add(mGetForecastInteractor.getObservableForecast()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread(), true)
                .subscribe(this::onForecastResponse));

        mCompositeDisposable.add(mUserPreferencesInteractor.getUserPref()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::getSettingsResponse));
    }

    public void onDetach() {
        mView = null;
        mCompositeDisposable.clear();
    }

    public void onDestroy() {
        mCompositeDisposable.dispose();
    }

    private void onForecastResponse(ResponseBundle<Forecast> bundle) {
        if (mView == null)
            return;

        if (bundle.isHasValue()) {
            mForecast = bundle.getResponse();
            mView.setCurrentDay(mForecast.getCurrent(), mForecast.getSettingPref());
            mView.setDataIntoAdapter(mForecast.getForecastForDayList());
        } else {
            mView.makeToast(bundle.getExceptions().getLocalizedMessage());
        }
        mView.hideProgress();
    }

    private void getSettingsResponse(ResponseBundle<UserPreferences> bundle) {
        if (mUserPreferences == null){
            mUserPreferences = bundle.getResponse();
            loadWeatherForecast();
        } else {
            if (bundle.isHasValue()){
                if (!mUserPreferences.equals(bundle.getResponse())){
                    mUserPreferences = bundle.getResponse();
                    loadWeatherForecast();
                }
            }
        }
    }

    public void loadWeatherForecast() {
        mView.startProgress();

        getForecastDisposable = mGetForecastInteractor.updateData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> getForecastDisposable.dispose());
    }

    public void setSelectedDay(int itemPosition) {
        mView.startProgress();
        Forecast.Day day = mForecast.getForecastForDayList().get(itemPosition);
        mCompositeDisposable.add(mSetSelectedDayInteractor.setSelectedDay(day)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::startNewScreen));
    }

    private void startNewScreen() {
        if (mView == null)
            return;
        mView.hideProgress();
        mView.starNewScreen();
    }
}
