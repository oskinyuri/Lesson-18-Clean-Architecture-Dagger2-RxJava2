package com.example.oskin.lesson_15_clean_architecture.Presentation.Presenters;

import android.os.Handler;
import android.os.Looper;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.UserPreferences;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.GetUserPreferencesInteractor;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.Callbacks.GetUserPrefCallback;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.Callbacks.SetUserPrefCallback;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.SetUserPreferencesInteractor;

import java.util.concurrent.ExecutorService;

public class SettingPresenter {

    private ExecutorService mExecutorService;
    private SetUserPreferencesInteractor mSetSettings;
    private GetUserPreferencesInteractor mGetSettings;
    private UserPreferences mUserPreferences;

    private ISettingView mView;

    private final Handler mHandler;

    public SettingPresenter(SetUserPreferencesInteractor setUserPreferencesInteractor,
                            GetUserPreferencesInteractor getUserPreferencesInteractor,
                            ExecutorService executorService,
                            Handler handler) {
        mSetSettings = setUserPreferencesInteractor;
        mGetSettings = getUserPreferencesInteractor;
        mExecutorService = executorService;
        mHandler = handler;
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
                mView.setUserPref(mUserPreferences);
            }
        });
    }

    public void SetUserPrefSettings(UserPreferences userPreferences){
        mUserPreferences = userPreferences;
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                mSetSettings.setUserPreferences(mUserPreferences, new SetUserPrefCallback() {
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

    public void getUserPrefSettings(){
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                mGetSettings.getUserPref(new GetUserPrefCallback() {
                    @Override
                    public void onResponse(UserPreferences userPreferences) {
                        mUserPreferences = userPreferences;
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
