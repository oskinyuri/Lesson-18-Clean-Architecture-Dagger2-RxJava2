package com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.presenters;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.ResponseBundle;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.UserPreferences;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.UserPreferencesInteractor;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SettingPresenter {
    private UserPreferencesInteractor mUserPrefInteractor;

    private ISettingView mView;


    private Disposable mSetDisposable;
    private Disposable mGetDisposable;

    public SettingPresenter(UserPreferencesInteractor userPreferencesInteractor) {
        mUserPrefInteractor = userPreferencesInteractor;
    }

    public void onAttach(ISettingView view) {
        mGetDisposable = mUserPrefInteractor.getUserPref()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::getSettingResponse);

        mView = view;
    }

    public void onDetach() {
        mView = null;

        // Отписываемся от источников
        mGetDisposable.dispose();
    }

    public void SetUserPrefSettings(UserPreferences userPreferences) {

        // Если не работает, может быть не правильно отписываюсь!!!
        mUserPrefInteractor.setUserPref(userPreferences)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mSetDisposable = d;
                    }

                    @Override
                    public void onComplete() {
                        showToast("Change saved.");
                        mSetDisposable.dispose();
                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast(e.getLocalizedMessage());
                        mSetDisposable.dispose();
                    }
                });
    }

    private void getSettingResponse(ResponseBundle<UserPreferences> bundle) {
        if (mView == null)
            return;

        if (bundle.isHasValue()) {
            mView.setUserPref(bundle.getResponse());
        } else {
            showToast(bundle.getExceptions().getLocalizedMessage());
        }
    }

    //TODO переделать
    public void initSpinnerAdapter() {
        Integer[] countDays = {1, 2, 3, 4, 5, 6, 7};
        mView.setSpinnerAdapter(countDays);
    }

    //TODO Сделать утилтный класс для скрывания клавы

    private void showToast(final String text) {
        if (mView == null)
            return;
        mView.makeToast(text);
    }
}
