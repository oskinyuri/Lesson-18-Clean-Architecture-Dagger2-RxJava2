package com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.Presenters;

import android.os.Handler;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.Forecast;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.ResponseBundle;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.GetForecastInteractor;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.SetSelectedDayInteractor;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.DI.Qualifier.SingleThread;

import java.util.concurrent.ExecutorService;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainWeekPresenter {

    private IMainWeekView mView;

    private Forecast mForecast;

    private GetForecastInteractor mGetForecastInteractor;
    private SetSelectedDayInteractor mSetSelectedDayInteractor;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public MainWeekPresenter(@SingleThread ExecutorService executorService,
                             Handler uiHandler,
                             GetForecastInteractor getForecastInteractor,
                             SetSelectedDayInteractor setSelectedDayInteractor) {

        mGetForecastInteractor = getForecastInteractor;
        mSetSelectedDayInteractor = setSelectedDayInteractor;
    }

    public void onCreate() {
        /**
         * Подписываемся на subject излучающий погоду.
         */
        mCompositeDisposable.add(mGetForecastInteractor
                .getObservableForecast()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread(), true)
                .subscribe(this::onResponse));

        //TODO setting observable
    }

    public void onAttach(IMainWeekView view) {
        mView = view;
    }

    public void onDetach() {
        mView = null;
    }

    public void onStop() {
        mCompositeDisposable.dispose();
    }

    private void onResponse(ResponseBundle<Forecast> bundle) {
        if (mView == null)
            return;
        if (bundle.isHasValue()) {
            mForecast = bundle.getResponse();
            mView.setCurrentDay(mForecast.getCurrent(), mForecast.getSettingPref());
            mView.setDataIntoAdapter(mForecast.getForecastForDayList());
            mView.hideProgress();
        } else {
            mView.makeToast(bundle.getExceptions().getLocalizedMessage());
        }
    }

    public void loadWeatherForecast() {
        mView.startProgress();

        mCompositeDisposable.add(mGetForecastInteractor.updateData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
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
