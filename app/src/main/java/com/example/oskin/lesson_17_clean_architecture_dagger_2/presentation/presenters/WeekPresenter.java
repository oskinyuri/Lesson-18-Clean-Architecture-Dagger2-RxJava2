package com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.presenters;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.Forecast;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.ResponseBundle;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.UserPreferences;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.GetForecastInteractor;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.SelectedDayInteractor;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.UserPreferencesInteractor;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WeekPresenter {

    private IMainWeekView mView;

    private Forecast mForecast;

    private GetForecastInteractor mGetForecastInteractor;
    private SelectedDayInteractor mSelectedDayInteractor;
    private UserPreferencesInteractor mUserPreferencesInteractor;

    private UserPreferences mUserPreferences;

    //TODO inject from dagger
    private CompositeDisposable mCompositeDisposable;

    private Disposable getForecastDisposable;


    public WeekPresenter(GetForecastInteractor getForecastInteractor,
                         SelectedDayInteractor selectedDayInteractor,
                         UserPreferencesInteractor userPreferencesInteractor,
                         CompositeDisposable compositeDisposable) {

        mGetForecastInteractor = getForecastInteractor;
        mSelectedDayInteractor = selectedDayInteractor;
        mUserPreferencesInteractor = userPreferencesInteractor;
        mCompositeDisposable = compositeDisposable;
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

        getForecastDisposable = mGetForecastInteractor.updateData(mUserPreferences)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> getForecastDisposable.dispose());
    }

    public void setSelectedDay(int itemPosition) {
        mView.startProgress();
        Forecast.Day day = mForecast.getForecastForDayList().get(itemPosition);
        mCompositeDisposable.add(mSelectedDayInteractor.setSelectedDay(day)
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
