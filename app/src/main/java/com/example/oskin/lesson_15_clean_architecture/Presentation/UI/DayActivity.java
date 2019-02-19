package com.example.oskin.lesson_15_clean_architecture.Presentation.UI;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.ForecastDTOOutput;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.UserPreferences;
import com.example.oskin.lesson_15_clean_architecture.Presentation.Presenters.DayPresenter;
import com.example.oskin.lesson_15_clean_architecture.Presentation.Presenters.IDayView;
import com.example.oskin.lesson_15_clean_architecture.R;

public class DayActivity extends AppCompatActivity implements IDayView {

    private TextView mDayOfWeek;
    private TextView mDate;
    private TextView mWindSpeed;
    private TextView mTemperature;
    private TextView mPrecip;
    private TextView mConditionText;
    private ImageView mConditionImg;

    private ForecastDTOOutput.Day mDay;
    private UserPreferences mPrefDTO;

    private DayPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        initViews();
        initActionBar();
        mPresenter = new DayPresenter();
    }

    //TODO почему главный экран грузиться по новой?
    private void initActionBar() {

        //TODO переопределить кнопку назад а то херня какая-то

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
        mPresenter.getDay();
    }

    @Override
    protected void onPause() {
        mPresenter.onDetach();
        super.onPause();
    }

    @Override
    public void setSharedPrefDto(UserPreferences prefDTO) {
        mPrefDTO = prefDTO;
    }

    @Override
    public void setSelectedDay(ForecastDTOOutput.Day day) {
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
