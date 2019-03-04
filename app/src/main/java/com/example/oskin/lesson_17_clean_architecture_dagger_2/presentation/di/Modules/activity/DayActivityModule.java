package com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Modules.activity;

import android.os.Handler;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.GetSelectedDayInteractor;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.UserPreferencesInteractor;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.interactors.interfaces.dip.IWeatherRepository;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Modules.scopes.DayActivityScope;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Qualifier.SingleThread;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.presenters.DayPresenter;

import java.util.concurrent.ExecutorService;

import dagger.Module;
import dagger.Provides;

@Module
public class DayActivityModule {

    @DayActivityScope
    @Provides
    public DayPresenter provideDayPresenter(@SingleThread ExecutorService executorService,
                                            GetSelectedDayInteractor getSelectedDayInteractor,
                                            UserPreferencesInteractor userPreferencesInteractor,
                                            Handler handler) {
        return new DayPresenter(executorService,
                getSelectedDayInteractor,
                userPreferencesInteractor,
                handler);
    }

    @DayActivityScope
    @Provides
    public GetSelectedDayInteractor provideGetSelectedDayInteractor(IWeatherRepository iWeatherRepository) {
        return new GetSelectedDayInteractor(iWeatherRepository);
    }

}
