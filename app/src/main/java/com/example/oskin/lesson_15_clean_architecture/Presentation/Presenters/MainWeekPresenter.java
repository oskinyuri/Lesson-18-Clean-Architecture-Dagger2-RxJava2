package com.example.oskin.lesson_15_clean_architecture.Presentation.Presenters;

import android.os.Handler;
import android.os.Looper;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.ForecastDTOOutput;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.Callbacks.GetForecastCallback;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.DIP.IWeatherRepository;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.Callbacks.SetSelectedDayCallback;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.GetForecastInteractor;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.SetSelectedDayInteractor;
import com.example.oskin.lesson_15_clean_architecture.WeatherApp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainWeekPresenter implements GetForecastCallback {

    private IMainWeekView mView;

    private ForecastDTOOutput mDTOOutput;
    private ForecastDTOOutput.Day mDay;

    private GetForecastInteractor mGetForecastInteractor;
    private SetSelectedDayInteractor mSetSelectedDayInteractor;
    private IWeatherRepository mRepository;
    private ExecutorService mExecutorService;

    private final Handler mHandler;

    public MainWeekPresenter() {
        mExecutorService = Executors.newSingleThreadExecutor();
        mRepository = WeatherApp.getWeatherRepository();
        mHandler = new Handler(Looper.getMainLooper());
        mGetForecastInteractor = new GetForecastInteractor(mRepository);
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
               mGetForecastInteractor.loadForecast(new GetForecastCallback() {
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
                mSetSelectedDayInteractor = new SetSelectedDayInteractor(mRepository);
                mSetSelectedDayInteractor.setSelectedDay(mDay, new SetSelectedDayCallback() {
                    @Override
                    public void onResponse() {
                        startNewScreen();
                    }
                });
            }
        });
    }


    private class LoadRunnable implements Runnable {
        private GetForecastCallback threadCallback;

        public LoadRunnable(GetForecastCallback callback) {
            threadCallback = callback;


        }

        @Override
        public void run() {
            mGetForecastInteractor.loadForecast(threadCallback);
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
