package com.example.oskin.lesson_15_clean_architecture.Presentation.Presenters;

import android.os.Handler;
import android.os.Looper;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.SharedPrefDTO;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.GetSharedPrefSettingsInteractor;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.GetSharedPrefCallback;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.ISettingsRepository;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.LoadSharedPrefCallback;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.LoadSharedPrefSettingsInteractor;
import com.example.oskin.lesson_15_clean_architecture.WeatherApp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SettingPresenter {

    private ISettingsRepository mRepository;
    private ExecutorService mExecutorService;
    private LoadSharedPrefSettingsInteractor mLoadSettings;
    private GetSharedPrefSettingsInteractor mGetSettings;
    private SharedPrefDTO mSharedPrefDTO;

    private ISettingView mView;

    private final Handler mHandler = new Handler(Looper.getMainLooper());

    public SettingPresenter() {
        mExecutorService = Executors.newSingleThreadExecutor();
        mRepository = WeatherApp.getSettingRepository();
    }

    public void onAttach(ISettingView view) {
        mView = view;
    }

    public void onDetach() {
        mView = null;
    }

    private void setSharedPresSettings(){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mView == null)
                    return;
                mView.setSettingsSharedPref(mSharedPrefDTO);
            }
        });
    }

    public void loadSharedPrefSettings(SharedPrefDTO sharedPrefDTO){
        mSharedPrefDTO = sharedPrefDTO;
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                mLoadSettings = new LoadSharedPrefSettingsInteractor(mRepository);
                mLoadSettings.loadSharedPreferences(mSharedPrefDTO, new LoadSharedPrefCallback() {
                    @Override
                    public void onResponse() {
                        showToast("Change saved.");
                    }

                    @Override
                    public void onFailure() {
                        showToast("City not founded.");
                    }
                });
            }
        });
    }

    public void getSharedPrefSettings(){
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                mGetSettings = new GetSharedPrefSettingsInteractor(mRepository);
                mGetSettings.getSharedPref(new GetSharedPrefCallback() {
                    @Override
                    public void onResponse(SharedPrefDTO sharedPrefDTO) {
                        mSharedPrefDTO = sharedPrefDTO;
                        setSharedPresSettings();
                    }
                });
            }
        });
    }

    public void initSpinnerAdapter(){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mView == null)
                    return;
                Integer[] countDays = {1,2,3,4,5,6,7};
                mView.setSpinnerAdapter(countDays);
            }
        });


    }

    //TODO Сделать утилтный класс для скрывания клавы

    private void showToast(final String text){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mView == null)
                    return;
                mView.makeToast(text);
            }
        });
    }


}
