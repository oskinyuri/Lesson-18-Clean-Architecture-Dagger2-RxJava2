package com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.Presenters;

import android.os.Handler;
import android.util.Log;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.Forecast;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.ResponseBundle;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.GetForecastInteractor;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.Callbacks.GetForecastCallback;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.Callbacks.SetSelectedDayCallback;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.SetSelectedDayInteractor;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.DI.Qualifier.SingleThread;

import java.util.concurrent.ExecutorService;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainWeekPresenter {

    private IMainWeekView mView;

    private Forecast mDTOOutput;
    private Forecast.Day mDay;

    private GetForecastInteractor mGetForecastInteractor;
    private SetSelectedDayInteractor mSetSelectedDayInteractor;
    private ExecutorService mExecutorService;

    private final Handler mUIHandler;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public MainWeekPresenter(@SingleThread ExecutorService executorService,
                             Handler uiHandler,
                             GetForecastInteractor getForecastInteractor,
                             SetSelectedDayInteractor setSelectedDayInteractor) {

        mExecutorService = executorService;
        mUIHandler = uiHandler;
        mGetForecastInteractor = getForecastInteractor;
        mSetSelectedDayInteractor = setSelectedDayInteractor;
    }

    public void onAttach(IMainWeekView view) {
        mView = view;
    }

    public void onDetach() {
        mCompositeDisposable.dispose();
        mView = null;
    }

    public void startNewScreen() {
        mUIHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mView == null) {
                    return;
                }
                mView.hideProgress();
                mView.starNewScreen();
                //mView.makeToast("network failure :(");
            }
        });
    }

    public void loadWeatherForecast() {
        mView.startProgress();


        // Start loading in new thread by rxJava.
        Completable.fromAction(() -> {
            mCompositeDisposable.add(mGetForecastInteractor
                    .loadForecast()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread(), true)
                    .subscribeWith(new DisposableObserver<ResponseBundle<Forecast>>() {
                        @Override
                        public void onNext(ResponseBundle<Forecast> forecastResponseBundle) {
                            if (mView == null)
                                return;
                            if (forecastResponseBundle.isHasValue()) {
                                mDTOOutput = forecastResponseBundle.getResponse();
                                mView.setCurrentDay(mDTOOutput.getCurrent(), mDTOOutput.getSettingPref());
                                mView.setDataIntoAdapter(mDTOOutput.getForecastForDayList());
                                mView.hideProgress();
                            } else {
                                mView.makeToast(forecastResponseBundle.getExceptions().getLocalizedMessage());
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            if (mView == null) {
                                return;
                            }
                            mView.hideProgress();
                            mView.makeToast(e.getMessage());
                        }

                        @Override
                        public void onComplete() {
                            mView.hideProgress();
                        }
                    }));
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    public void setSelectedDay(int itemPosition){
        mView.startProgress();
        mDay = mDTOOutput.getForecastForDayList().get(itemPosition);

        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                mSetSelectedDayInteractor.setSelectedDay(mDay, new SetSelectedDayCallback() {
                    @Override
                    public void onResponse() {
                        startNewScreen();
                    }
                });
            }
        });
    }
}
