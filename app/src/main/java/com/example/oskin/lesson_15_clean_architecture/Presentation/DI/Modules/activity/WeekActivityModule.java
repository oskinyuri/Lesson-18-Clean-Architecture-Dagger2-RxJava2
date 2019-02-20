package com.example.oskin.lesson_15_clean_architecture.Presentation.DI.Modules.activity;

import android.content.Context;
import android.os.Handler;
import android.view.View;

import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.GetForecastInteractor;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.Interfaces.DIP.IWeatherRepository;
import com.example.oskin.lesson_15_clean_architecture.Domain.Interactors.SetSelectedDayInteractor;
import com.example.oskin.lesson_15_clean_architecture.Presentation.DI.Modules.scopes.WeekActivityScope;
import com.example.oskin.lesson_15_clean_architecture.Presentation.DI.Qualifier.ActivityContext;
import com.example.oskin.lesson_15_clean_architecture.Presentation.DI.Qualifier.SingleThread;
import com.example.oskin.lesson_15_clean_architecture.Presentation.Presenters.MainWeekPresenter;
import com.example.oskin.lesson_15_clean_architecture.Presentation.UI.ForecastAdapter;
import com.example.oskin.lesson_15_clean_architecture.Presentation.UI.MainWeekActivity;

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
    public MainWeekPresenter provideMainWeekPresenter(
            @SingleThread ExecutorService executorService,
            Handler uiHandler,
            GetForecastInteractor getForecastInteractor,
            SetSelectedDayInteractor setSelectedDayInteractor) {

        return new MainWeekPresenter(
                executorService,
                uiHandler,
                getForecastInteractor,
                setSelectedDayInteractor);
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
