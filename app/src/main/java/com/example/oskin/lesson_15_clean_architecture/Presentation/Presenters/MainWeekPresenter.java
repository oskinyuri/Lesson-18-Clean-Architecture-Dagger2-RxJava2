package com.example.oskin.lesson_15_clean_architecture.Presentation.Presenters;

import android.os.Handler;
import android.os.Looper;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.ForecastDTOOutput;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.ILoadDTOCallback;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.IRepository;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.LoadWeatherForecast;
import com.example.oskin.lesson_15_clean_architecture.WeatherApp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainWeekPresenter implements ILoadDTOCallback {

    private IMainWeekView mView;
    private LoadWeatherForecast mLoadWeatherForecast;
    private ForecastDTOOutput mDTOOutput;
    private IRepository mRepository;

    private final Handler handler = new Handler(Looper.getMainLooper());

    public void onAttach(IMainWeekView view) {
        mView = view;
    }

    public void onDetach() {
        mView = null;
    }


    @Override
    public void onResponse(ForecastDTOOutput dtoOutput) {
        mDTOOutput = dtoOutput;
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (mView == null)
                    return;
                mView.setCurrentDay(mDTOOutput.getCurrent(), mDTOOutput.getSettingPref());
                mView.setDataIntoAdapter(mDTOOutput.getForecastForDayList());
                mView.hideProgress();
                mView.makeToast("Weather forecast loaded.");
            }
        });
    }

    @Override
    public void onFailure() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (mView == null) {
                    return;
                }
                mView.hideProgress();
                mView.makeToast("network failure :(");
            }
        });
    }

    public void startNewScreen() {

    }


    public void loadWeatherForecast() {
        mView.startProgress();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new LoadRunnable(this));
    }

    //TODO move it in setting presenter
    /**private boolean validateCity(String city) {
        for (Cities c : Cities.values()) {
            if (c.name().equals(city))
                return true;
        }
        return false;
    }

    private boolean validateCountDays(int countDays) {
        if ((Days.One.getValue() <= countDays) && (countDays <= Days.Seven.getValue()))
            return true;
        return false;
    }**/

    private class LoadRunnable implements Runnable {
        private ILoadDTOCallback threadCallback;

        public LoadRunnable(ILoadDTOCallback callback) {
            threadCallback = callback;
        }

        @Override
        public void run() {
            mRepository = WeatherApp.getRepository();
            mLoadWeatherForecast = new LoadWeatherForecast(mRepository);
            mLoadWeatherForecast.loadForecast(threadCallback);
        }
    }
}
