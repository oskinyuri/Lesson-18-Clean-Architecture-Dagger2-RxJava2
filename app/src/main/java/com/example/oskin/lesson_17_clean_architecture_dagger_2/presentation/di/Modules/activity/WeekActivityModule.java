package com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Modules.activity;

import android.content.Context;
import android.os.Handler;
import android.view.View;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.GetForecastInteractor;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.UserPreferencesInteractor;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.interfaces.dip.IWeatherRepository;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.SetSelectedDayInteractor;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Modules.scopes.WeekActivityScope;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Qualifier.ActivityContext;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Qualifier.SingleThread;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.presenters.WeekPresenter;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.ui.ForecastAdapter;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.ui.MainWeekActivity;

import java.util.concurrent.ExecutorService;

import dagger.Module;
import dagger.Provides;

@Module
public class WeekActivityModule {

    private final MainWeekActivity mainWeekActivity;

    public WeekActivityModule(MainWeekActivity mainWeekActivity){
        this.mainWeekActivity = mainWeekActivity;
    }

    @WeekActivityScope
    @Provides
    @ActivityContext
    public Context provideContext(){
        return mainWeekActivity;
    }

    @WeekActivityScope
    @Provides
    public WeekPresenter provideMainWeekPresenter(
            @SingleThread ExecutorService executorService,
            Handler uiHandler,
            GetForecastInteractor getForecastInteractor,
            SetSelectedDayInteractor setSelectedDayInteractor,
            UserPreferencesInteractor userPreferencesInteractor) {

        return new WeekPresenter(
                executorService,
                uiHandler,
                getForecastInteractor,
                setSelectedDayInteractor,
                userPreferencesInteractor);
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
    public SetSelectedDayInteractor provideSetSelectedDayInteractor(IWeatherRepository iWeatherRepository) {
        return new SetSelectedDayInteractor(iWeatherRepository);
    }

    @WeekActivityScope
    @Provides
    public View.OnClickListener provideListener(){
        return mainWeekActivity;
    }

}
