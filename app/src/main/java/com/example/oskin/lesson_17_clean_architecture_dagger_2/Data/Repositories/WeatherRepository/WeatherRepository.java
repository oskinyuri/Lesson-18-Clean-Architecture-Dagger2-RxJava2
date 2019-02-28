package com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.Repositories.WeatherRepository;

import android.content.Context;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.DB.DatabaseManager;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.Entity.DTO.LastRequestInfo;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.Entity.WeatherModel.WeatherModel;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.SharedPreferences.WeatherPreferences.SharedPrefManager;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.Web.RemoteDataSource;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.Forecast;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.ResponseBundle;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.UserPreferences;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.Callbacks.GetSelectedDayCallback;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.DIP.IWeatherRepository;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class WeatherRepository implements IWeatherRepository {

    private Context mContext;

    private RemoteDataSource mRemoteSource;
    private DatabaseManager mLocalSource;
    private ISharedPrefManager mPrefManager;

    //TODO Interface interaction
    private WeatherModel mWeatherModelResponse;
    private Forecast mForecast;
    private UserPreferences mUserPreferences;
    private WeatherMapper mWeatherMapper;

    // TODO inject from dagger
    PublishSubject<ResponseBundle<Forecast>> mSubject = PublishSubject.create();

    public WeatherRepository(Context context,
                             RemoteDataSource remoteDataSource,
                             DatabaseManager databaseManager,
                             SharedPrefManager sharedPrefManager,
                             WeatherMapper weatherMapper) {

        mWeatherMapper = weatherMapper;
        mContext = context;
        mRemoteSource = remoteDataSource;
        mLocalSource = databaseManager;
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

        mWeatherModelResponse = loadFromDB(mUserPreferences);
        if (mWeatherModelResponse != null) {
            mForecast = mWeatherMapper.getDTOFromPOJO(mWeatherModelResponse, mUserPreferences);
            mSubject.onNext(new ResponseBundle<>(mForecast));
        } else {
            mSubject.onNext(new ResponseBundle<>(new Exception("No cached data.")));
        }
    }

    private void loadFromWeb() {

        /*//TODO delete it. Testing staff.
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        if (!isDataRelevant()) {
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
                mWeatherModelResponse = loadFromDB(mUserPreferences);
                mForecast = mWeatherMapper.getDTOFromPOJO(mWeatherModelResponse, mUserPreferences);

                /**
                 * Сохранения последнего запроса.
                 */
                createLastRequest();

                mSubject.onNext(new ResponseBundle<>(mForecast));
            } else {
                mSubject.onNext(new ResponseBundle<>(new Exception("No internet connection.")));
            }
        }
    }

    private void createLastRequest() {
        LastRequestInfo info = new LastRequestInfo();
        info.setLastTimeInEpoch(mWeatherModelResponse.getLocation().getLocaltimeEpoch());
        info.setLastCityName(mWeatherModelResponse.getCityName());
        info.setLastCountDays(mUserPreferences.getCountDays());
        mPrefManager.setLastRequest(info);
    }

    private WeatherModel loadFromDB(UserPreferences request) {
        return mLocalSource.getForecast(request.getCityName());
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
                && (timeDifference <= maxDifference)
                && (currentCountDays <= lastCountDays));
    }

    @Override
    public Completable setSelectedDay(Forecast.Day day) {
        return Completable.fromAction(() -> mPrefManager.setSelectedDay(day));
    }

    @Override
    public void getSelectedDay(GetSelectedDayCallback callback) {
        callback.onResponse(mPrefManager.getSelectedDay());
    }
}
