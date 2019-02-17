package com.example.oskin.lesson_15_clean_architecture.Presentation.Presenters;

import android.os.Handler;
import android.os.Looper;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.ForecastDTOOutput;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.ILoadDTOCallback;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.IWeatherRepository;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.SetSelectedDayCallback;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.LoadWeatherForecast;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.SetSelectedDay;
import com.example.oskin.lesson_15_clean_architecture.WeatherApp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainWeekPresenter implements ILoadDTOCallback {

    private IMainWeekView mView;

    private ForecastDTOOutput mDTOOutput;
    private ForecastDTOOutput.Day mDay;

    private LoadWeatherForecast mLoadWeatherForecast;
    private SetSelectedDay mSetSelectedDay;
    private IWeatherRepository mRepository;
    private ExecutorService mExecutorService;

    private final Handler mHandler;

    public MainWeekPresenter() {
        mExecutorService = Executors.newSingleThreadExecutor();
        mRepository = WeatherApp.getWeatherRepository();
        mHandler = new Handler(Looper.getMainLooper());
        mLoadWeatherForecast = new LoadWeatherForecast(mRepository);
    }

    public void onAttach(IMainWeekView view) {
        mView = view;
    }

    public void onDetach() {
        mView = null;
    }


    @Override
    public void onResponse(ForecastDTOOutput dtoOutput) {
        mDTOOutput = dtoOutput;
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mView == null)
                    return;
                mView.setCurrentDay(mDTOOutput.getCurrent(), mDTOOutput.getSettingPref());
                mView.setDataIntoAdapter(mDTOOutput.getForecastForDayList());
                mView.hideProgress();
            }
        });
    }

    @Override
    public void onFailure() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mView == null) {
                    return;
                }
                mView.hideProgress();
                mView.makeToast("No internet connection. Loaded cached forecast.");
            }
        });
    }

    public void startNewScreen() {
        mHandler.post(new Runnable() {
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

        mExecutorService.execute(new Runnable() {
            @Override
            public void run() {
               mLoadWeatherForecast.loadForecast(new ILoadDTOCallback() {
                   @Override
                   public void onResponse(ForecastDTOOutput dtoOutput) {
                       MainWeekPresenter.this.onResponse(dtoOutput);
                   }

                   @Override
                   public void onFailure() {
                       MainWeekPresenter.this.onFailure();
                   }
               });
            }
        });
        //mExecutorService.submit(new LoadRunnable(this));
    }

    public void setSelectedDay(int itemPosition){
        mView.startProgress();
        mDay = mDTOOutput.getForecastForDayList().get(itemPosition);

        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                mSetSelectedDay = new SetSelectedDay(mRepository);
                mSetSelectedDay.setSelectedDay(mDay, new SetSelectedDayCallback() {
                    @Override
                    public void onResponse() {
                        startNewScreen();
                    }
                });
            }
        });
    }


    private class LoadRunnable implements Runnable {
        private ILoadDTOCallback threadCallback;

        public LoadRunnable(ILoadDTOCallback callback) {
            threadCallback = callback;


        }

        @Override
        public void run() {
            mLoadWeatherForecast.loadForecast(threadCallback);
        }
    }
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
