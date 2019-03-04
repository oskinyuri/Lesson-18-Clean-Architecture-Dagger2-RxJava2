package com.example.oskin.lesson_17_clean_architecture_dagger_2.data.repositories.weatherRepository;

import android.content.Context;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.data.db.LocalDataSource;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.data.entity.DTO.LastRequestInfo;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.data.entity.WeatherModel.WeatherModel;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.data.sharedPreferences.weatherPreferences.SharedPrefManager;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.data.web.RemoteDataSource;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.Forecast;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.ResponseBundle;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.UserPreferences;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.interfaces.dip.IWeatherRepository;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.subjects.PublishSubject;

public class WeatherRepository implements IWeatherRepository {

    private Context mContext;

    //TODO Interface interaction with data source
    //Data sources
    private RemoteDataSource mRemoteSource;
    private LocalDataSource mLocalSource;

    //Mappers
    private WeatherMapper mWeatherMapper;

    // Data source response
    private WeatherModel mWeatherModelResponse;

    // Mapper response for use-case
    private Forecast mForecastResponse;

    //TODO in other repo
    private UserPreferences mUserPreferences;
    private ISharedPrefManager mPrefManager;

    // TODO inject from dagger
    PublishSubject<ResponseBundle<Forecast>> mSubject = PublishSubject.create();

    public WeatherRepository(Context context,
                             RemoteDataSource remoteDataSource,
                             LocalDataSource localDataSource,
                             SharedPrefManager sharedPrefManager,
                             WeatherMapper weatherMapper) {

        mWeatherMapper = weatherMapper;
        mContext = context;
        mRemoteSource = remoteDataSource;
        mLocalSource = localDataSource;
        mPrefManager = sharedPrefManager;
    }

    @Override
    public Completable updateData() {
        return Completable.fromAction(() -> {
            WeatherRepository.this.loadFromBD();
            WeatherRepository.this.loadFromWeb();
        });
    }

    @Override
    public Observable<ResponseBundle<Forecast>> getObservableForecast() {
        return mSubject;
    }

    private void loadFromBD() {
        mUserPreferences = mPrefManager.getSharedPrefInDTO();

        if (isDataRelevant()){

            mWeatherModelResponse = mLocalSource.getForecast(mUserPreferences.getCityName());
            if (mWeatherModelResponse != null) {
                mForecastResponse = mWeatherMapper.getDTOFromPOJO(mWeatherModelResponse, mUserPreferences);
                mSubject.onNext(new ResponseBundle<>(mForecastResponse));
            } else {
                mSubject.onNext(new ResponseBundle<>(new Exception("No cached data.")));
            }
        } else {
            mSubject.onNext(new ResponseBundle<>(new Exception("No cached data.")));
        }


    }

    private void loadFromWeb() {
        mWeatherModelResponse = mRemoteSource.getForecast(
                mUserPreferences.getCityName(),
                mUserPreferences.getCountDays());

        if (mWeatherModelResponse != null) {

            /**
             * Мапинг ответа сети для БД.
             */
            mWeatherModelResponse = mWeatherMapper.getDBModelFromResponse(mWeatherModelResponse, mUserPreferences);
            mLocalSource.addWeatherModel(mWeatherModelResponse);

            /**
             * Маппинг ответа БД для внутреннего слоя.
             */
            mLocalSource.getForecast(mUserPreferences.getCityName());
            mForecastResponse = mWeatherMapper.getDTOFromPOJO(mWeatherModelResponse, mUserPreferences);

            /**
             * Сохранения последнего запроса.
             */
            createLastRequest();

            mSubject.onNext(new ResponseBundle<>(mForecastResponse));
        } else {
            mSubject.onNext(new ResponseBundle<>(new Exception("No internet connection.")));
        }
    }

    private void createLastRequest() {
        LastRequestInfo info = new LastRequestInfo();
        info.setLastTimeInEpoch(mWeatherModelResponse.getLocation().getLocaltimeEpoch());
        info.setLastCityName(mWeatherModelResponse.getCityName());
        info.setLastCountDays(mUserPreferences.getCountDays());
        mPrefManager.setLastRequest(info);
    }

    private boolean isDataRelevant() {

        LastRequestInfo info = mPrefManager.getLastRequest();

        String cityNameCurrent = mUserPreferences.getCityName();
        String cityNameLast = info.getLastCityName();

        int currentCountDays = mUserPreferences.getCountDays();
        int lastCountDays = info.getLastCountDays();

        long lastUpdateTime = info.getLastTimeInEpoch();
        long currentTime = System.currentTimeMillis();

        //TODO return 15 minutes
        long maxDifference = 1 * 60 * 1000;
        long timeDifference = currentTime - lastUpdateTime * 1000;

        return ((cityNameCurrent.equals(cityNameLast))
                //&& (timeDifference <= maxDifference)
                && (currentCountDays <= lastCountDays));
    }

    @Override
    public Completable setSelectedDay(Forecast.Day day) {
        return Completable.fromAction(() -> mPrefManager.setSelectedDay(day));
    }

    @Override
    public Single<Forecast.Day> getSelectedDay() {
        return Single.fromCallable(() -> mPrefManager.getSelectedDay());
    }
}
