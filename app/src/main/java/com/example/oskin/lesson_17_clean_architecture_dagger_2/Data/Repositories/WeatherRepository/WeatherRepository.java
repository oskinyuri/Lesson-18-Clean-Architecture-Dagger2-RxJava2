package com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.Repositories.WeatherRepository;

import android.content.Context;
import android.util.Log;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.DB.DatabaseManager;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.Entity.DTO.LastRequestInfo;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.Entity.WeatherModel.WeatherModel;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.SharedPreferences.WeatherPreferences.SharedPrefManager;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.Web.ApiMapper;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.Forecast;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.ResponseBundle;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.UserPreferences;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.Callbacks.GetSelectedDayCallback;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.Callbacks.GetForecastCallback;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.DIP.IWeatherRepository;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.Callbacks.SetSelectedDayCallback;

import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableConverter;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

public class WeatherRepository implements IWeatherRepository {

    private Context mContext;

    private ApiMapper mMapper;
    private DatabaseManager mDBManager;
    private ISharedPrefManager mPrefManager;

    //TODO Interface interaction
    private WeatherModel mWeatherModelResponse;
    private Forecast mForecast;
    private UserPreferences mUserPreferences;
    private WeatherMapper mWeatherMapper;

    // TODO inject from dagger
    ReplaySubject<ResponseBundle<Forecast>> mSubject;

    public WeatherRepository(Context context,
                             ApiMapper apiMapper,
                             DatabaseManager databaseManager,
                             SharedPrefManager sharedPrefManager,
                             WeatherMapper weatherMapper) {
        mWeatherMapper = weatherMapper;
        mContext = context;
        mMapper = apiMapper;
        mDBManager = databaseManager;
        mPrefManager = sharedPrefManager;
    }

    // New method
    @Override
    public Observable<ResponseBundle<Forecast>> loadWeatherForecast() {
        mSubject = ReplaySubject.create();
        Completable
                .fromAction(() -> {
            WeatherRepository.this.loadFromBD();
            WeatherRepository.this.loadFromWeb();
        })
                .subscribeOn(Schedulers.io())
                .subscribe();

        return mSubject;
    }

    private void loadFromBD() {
        mUserPreferences = mPrefManager.getSharedPrefInDTO();

        mWeatherModelResponse = loadFromDB(mUserPreferences);
        if (mWeatherModelResponse != null) {
            mForecast = mWeatherMapper.getDTOFromPOJO(mWeatherModelResponse, mUserPreferences);
            mSubject.onNext(new ResponseBundle<>(mForecast));
        } else {
            mSubject.onNext(new ResponseBundle<>(new Exception("No cached data.")));
        }
    }

    //New methods
    private void loadFromWeb() {

        //TODO delete it. Testing staff.
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!isDataRelevant()) {
            mWeatherModelResponse = loadFromWeb(mUserPreferences);
            if (mWeatherModelResponse != null) {

                createLastRequest();

                mWeatherModelResponse = mWeatherMapper.getDBModelFromResponse(mWeatherModelResponse, mUserPreferences);
                mDBManager.addWeatherModel(mWeatherModelResponse);

                mWeatherModelResponse = loadFromDB(mUserPreferences);
                mForecast = mWeatherMapper.getDTOFromPOJO(mWeatherModelResponse, mUserPreferences);

                mSubject.onNext(new ResponseBundle<>(mForecast));
            } else {
                mSubject.onNext(new ResponseBundle<>(new Exception("No internet connection.")));
            }
            mSubject.onComplete();
        }
    }

    private void createLastRequest() {
        LastRequestInfo info = new LastRequestInfo();
        info.setLastTimeInEpoch(mWeatherModelResponse.getLocation().getLocaltimeEpoch());
        info.setLastCityName(mWeatherModelResponse.getCityName());
        info.setLastCountDays(mUserPreferences.getCountDays());
        mPrefManager.setLastRequest(info);
    }

    @Override
    public void setSelectedDay(Forecast.Day day, SetSelectedDayCallback callback) {
        mPrefManager.setSelectedDay(day);
        callback.onResponse();
    }

    @Override
    public void getSelectedDay(GetSelectedDayCallback callback) {
        callback.onResponse(mPrefManager.getSelectedDay());
    }

    private WeatherModel loadFromDB(UserPreferences request) {
        return mDBManager.getWeatherModel(request.getCityName());
    }

    private WeatherModel loadFromWeb(UserPreferences request) {
        return mMapper.loadForecast(request.getCityCoordinatesToString(), request.getCountDays());
    }

    private boolean isDataRelevant() {

        LastRequestInfo info = mPrefManager.getLastRequest();

        String cityNameCurrent = mUserPreferences.getCityName();
        long currentTime = System.currentTimeMillis();
        int currentCountDays = mUserPreferences.getCountDays();

        String cityNameLast = info.getLastCityName();
        long lastUpdateTime = info.getLastTimeInEpoch();
        int lastCountDays = info.getLastCountDays();

        //TODO return 15 minutes
        long maxDifference = 1 * 60 * 1000;
        long timeDifference = currentTime - lastUpdateTime * 1000;

        return ((cityNameCurrent.equals(cityNameLast))
                && (timeDifference <= maxDifference)
                && (currentCountDays <= lastCountDays));
    }
}
