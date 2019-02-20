package com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.Presenters;

import android.os.Handler;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.ForecastDTOOutput;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.GetForecastInteractor;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.Callbacks.GetForecastCallback;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.Callbacks.SetSelectedDayCallback;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.SetSelectedDayInteractor;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.DI.Qualifier.SingleThread;

import java.util.concurrent.ExecutorService;

public class MainWeekPresenter implements GetForecastCallback {

    private IMainWeekView mView;

    private ForecastDTOOutput mDTOOutput;
    private ForecastDTOOutput.Day mDay;

    private GetForecastInteractor mGetForecastInteractor;
    private SetSelectedDayInteractor mSetSelectedDayInteractor;
    private ExecutorService mExecutorService;

    private final Handler mUIHandler;

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
        mView = null;
    }


    @Override
    public void onResponse(ForecastDTOOutput dtoOutput) {
        mDTOOutput = dtoOutput;
        mUIHandler.post(new Runnable() {
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
        mUIHandler.post(new Runnable() {
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
