package com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Modules.activity;

import android.content.Context;
import android.os.Handler;
import android.view.View;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.GetForecastInteractor;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.SelectedDayInteractor;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.UserPreferencesInteractor;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.interfaces.dip.IWeatherRepository;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Modules.scopes.WeekActivityScope;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Qualifier.ActivityContext;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Qualifier.SingleThread;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.presenters.WeekPresenter;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.ui.ForecastAdapter;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.ui.WeekActivity;

import java.util.concurrent.ExecutorService;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class WeekActivityModule {

    private final WeekActivity mWeekActivity;

    public WeekActivityModule(WeekActivity weekActivity){
        this.mWeekActivity = weekActivity;
    }

    @WeekActivityScope
    @Provides
    @ActivityContext
    public Context provideContext(){
        return mWeekActivity;
    }

    @WeekActivityScope
    @Provides
    public WeekPresenter provideMainWeekPresenter(
            GetForecastInteractor getForecastInteractor,
            SelectedDayInteractor selectedDayInteractor,
            UserPreferencesInteractor userPreferencesInteractor,
            CompositeDisposable compositeDisposable) {

        return new WeekPresenter(
                getForecastInteractor,
                selectedDayInteractor,
                userPreferencesInteractor,
                compositeDisposable);
    }

    @WeekActivityScope
    @Provides
    public ForecastAdapter provideForecastAdapter(View.OnClickListener listener,
                                                  @ActivityContext Context context){
        return new ForecastAdapter(listener, context);
    }

    @WeekActivityScope
    @Provides
    public GetForecastInteractor provideGetForecastInteractor(IWeatherRepository iWeatherRepository) {
        return new GetForecastInteractor(iWeatherRepository);
    }

    @WeekActivityScope
    @Provides
    public SelectedDayInteractor provideSelectedDayInteractor(IWeatherRepository iWeatherRepository) {
        return new SelectedDayInteractor(iWeatherRepository);
    }

    @WeekActivityScope
    @Provides
    public View.OnClickListener provideListener(){
        return mWeekActivity;
    }

    @WeekActivityScope
    @Provides
    public CompositeDisposable provideCompositeDisposable(){
        return new CompositeDisposable();
    }

}
