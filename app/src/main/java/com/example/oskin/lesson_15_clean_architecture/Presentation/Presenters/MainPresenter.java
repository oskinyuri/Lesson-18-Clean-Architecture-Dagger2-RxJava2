package com.example.oskin.lesson_15_clean_architecture.Presentation.Presenters;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.WeatherForecastRequest;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.Enums.Cities;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.Enums.Days;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.WeatherModel.Forecast;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.ILoadCallback;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.LoadWeatherForecast;

public class MainPresenter implements ILoadCallback {

    private IMainView mView;
    private LoadWeatherForecast mLoadWeatherForecast;
    private WeatherForecastRequest mRequest;
    private Forecast mForecastResponse;

    public void onAttach(IMainView view) {
        mView = view;
        //TODO set city for location
        setViewCityName(Cities.Moscow.name());
        setViewCountDays(Days.Ten.getValue());
    }

    public void onDetach() {
        mView = null;
    }

    @Override
    public void onResponse() {
        /*mForecastResponse = forecast;
        mView.startActivity(mForecastResponse);*/
    }

    @Override
    public void onFailure() {
        mView.makeToast("Some error.");
    }

    public void startNewScreen() {

    }

    /**
     * Получает параметры для создания запроса и создает запрос
     *
     * @param city
     * @param countDays
     */
    public void loadWeatherForecast(String city, int countDays) {

        WeatherForecastRequest request = new WeatherForecastRequest();
        request.setCityName(city);
        request.setCountDays(countDays);

        if (!validateRequest(request))
            return;

        /**
         * Load forecast in new thread.
         */

        Thread thread = new Thread(new LoadRunnable(this));
        thread.start();


    }

    private boolean validateRequest(WeatherForecastRequest request) {

        if (!validateCity(request.getCityName())) {
            mView.makeToast("Not valid city or days count.");
            return false;
        }
        if (!validateCountDays(request.getCountDays())) {
            mView.makeToast("Not valid city or days count.");
            return false;
        }

        mRequest = request;
        return true;
    }

    private boolean validateCity(String city) {
        for (Cities c : Cities.values()) {
            if (c.name().equals(city))
                return true;
        }
        return false;
    }

    private boolean validateCountDays(int countDays) {
        if ((Days.One.getValue() <= countDays) && (countDays <= Days.Ten.getValue()))
            return true;
        return false;
    }

    private void setViewCityName(String cityName) {
        mView.setCityName(cityName);
    }

    private void setViewCountDays(int countDays) {
        mView.setCountDays(countDays);
    }

    private class LoadRunnable implements Runnable {
        private ILoadCallback threadCallback;

        public LoadRunnable(ILoadCallback callback) {
            threadCallback = callback;
        }

        @Override
        public void run() {
            mLoadWeatherForecast = new LoadWeatherForecast();
            mLoadWeatherForecast.loadForecast(mRequest, threadCallback);
        }
    }
}
