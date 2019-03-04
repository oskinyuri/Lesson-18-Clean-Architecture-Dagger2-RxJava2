package com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.WeatherApp;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.Forecast;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto.UserPreferences;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Components.DaggerDayActivityComponent;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Components.DayActivityComponent;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.di.Modules.activity.DayActivityModule;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.presenters.DayPresenter;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.presentation.presenters.IDayView;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.R;

import javax.inject.Inject;

public class DayActivity extends AppCompatActivity implements IDayView {

    private TextView mDayOfWeek;
    private TextView mDate;
    private TextView mWindSpeed;
    private TextView mTemperature;
    private TextView mPrecip;
    private TextView mConditionText;
    private ImageView mConditionImg;

    private Forecast.Day mDay;
    private UserPreferences mPrefDTO;

    private DayActivityComponent mComponent;

    @Inject
    DayPresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        initViews();
        initActionBar();

        mComponent = DaggerDayActivityComponent.builder()
                .weekActivityComponent(WeekActivity.getInstance().getWeekActivityComponent())
                .dayActivityModule(new DayActivityModule())
                .build();
        mComponent.injectActivity(DayActivity.this);

    }

    private void initActionBar() {

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(getString(R.string.forecast_on_day));
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }

    private void initViews() {
        mDayOfWeek = findViewById(R.id.day_day_of_week_text_view);
        mDate = findViewById(R.id.day_date_text_view);
        mWindSpeed = findViewById(R.id.day_wind_speed_text_view);
        mTemperature = findViewById(R.id.day_temperature_text_view);
        mPrecip = findViewById(R.id.day_precipitation_text_view);
        mConditionText = findViewById(R.id.day_condition_text_view);
        mConditionImg = findViewById(R.id.day_condition_img);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onAttach(this);
    }

    @Override
    protected void onPause() {
        mPresenter.onDetach();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void setSharedPrefDto(UserPreferences prefDTO) {
        mPrefDTO = prefDTO;
    }

    @Override
    public void setSelectedDay(Forecast.Day day) {
        mDay = day;
    }

    @Override
    public void displayData() {
        mDayOfWeek.setText(mDay.getDayOfWeek());
        mDate.setText(mDay.getDate());

        if (mPrefDTO.isKm()) {
            mWindSpeed.setText(getResources().getString(
                    R.string.wind_speed_in_kmh,
                    String.valueOf(mDay.getWind())));
        } else {
            mWindSpeed.setText(getResources().getString(
                    R.string.wind_speed_in_mph,
                    String.valueOf(mDay.getWind())));
        }

        mTemperature.setText(getResources().getString(
                R.string.min_max_temperature,
                String.valueOf(mDay.getMinTemp()),
                String.valueOf(mDay.getMaxTemp())));

        if (mPrefDTO.isMm()) {
            mPrecip.setText(getResources().getString(
                    R.string.precipiation_in_mm,
                    String.valueOf(mDay.getPrecip())));
        } else {
            mPrecip.setText(getResources().getString(
                    R.string.precipiation_in_mm,
                    String.valueOf(mDay.getPrecip())));
        }
        mConditionText.setText(mDay.getConditionText());

        String url = "http:" + mDay.getConditionImgUrl();
        Glide.with(DayActivity.this)
                .load(url)
                .into(mConditionImg);
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, DayActivity.class);
    }
}
