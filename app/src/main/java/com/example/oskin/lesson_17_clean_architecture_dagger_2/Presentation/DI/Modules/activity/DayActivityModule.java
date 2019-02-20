package com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.DI.Modules.activity;

import android.os.Handler;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.GetSelectedDayInteractor;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.GetUserPreferencesInteractor;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Interactors.Interfaces.DIP.IWeatherRepository;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.DI.Modules.scopes.DayActivityScope;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.DI.Qualifier.SingleThread;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.Presenters.DayPresenter;

import java.util.concurrent.ExecutorService;

import dagger.Module;
import dagger.Provides;

@Module
public class DayActivityModule {

    @DayActivityScope
    @Provides
    public DayPresenter provideDayPresenter(@SingleThread ExecutorService executorService,
                                            GetSelectedDayInteractor getSelectedDayInteractor,
                                            GetUserPreferencesInteractor getUserPreferencesInteractor,
                                            Handler handler) {
        return new DayPresenter(executorService,
                getSelectedDayInteractor,
                getUserPreferencesInteractor,
                handler);
    }

    @DayActivityScope
    @Provides
    public GetSelectedDayInteractor provideGetSelectedDayInteractor(IWeatherRepository iWeatherRepository) {
        return new GetSelectedDayInteractor(iWeatherRepository);
    }

}
