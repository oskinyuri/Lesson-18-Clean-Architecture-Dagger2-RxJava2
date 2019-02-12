package com.example.oskin.lesson_15_clean_architecture.Presentation.Presenters;

import android.os.Handler;
import android.os.Looper;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.SharedPrefDTO;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.GetSharedPreferencesSettings;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.GetSharedPrefCallback;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.IRepository;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.LoadSharedPrefCallback;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.LoadSharedPreferencesSettings;
import com.example.oskin.lesson_15_clean_architecture.WeatherApp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SettingPresenter {

    private IRepository mRepository;
    private ExecutorService mExecutorService;
    private LoadSharedPreferencesSettings mLoadSettings;
    private GetSharedPreferencesSettings mGetSettings;
    private SharedPrefDTO mSharedPrefDTO;

    private ISettingView mView;

    private final Handler mHandler = new Handler(Looper.getMainLooper());

    public SettingPresenter() {
        mExecutorService = Executors.newSingleThreadExecutor();
        mRepository = WeatherApp.getRepository();
    }

    public void onAttach(ISettingView view) {
        mView = view;
    }

    public void onDetach() {
        mView = null;
    }

    public void getSharedPrefSettings(){
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
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

    public void loadSharedPrefSettings(SharedPrefDTO sharedPrefDTO){
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                mLoadSettings.loadSharedPreferences(mSharedPrefDTO, new LoadSharedPrefCallback() {
                    @Override
                    public void onResponse() {
                        showToast();
                    }
                });
            }
        });
    }

    private void showToast(){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mView == null)
                    return;
                mView.makeToast("Change saved.");
            }
        });
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


}
