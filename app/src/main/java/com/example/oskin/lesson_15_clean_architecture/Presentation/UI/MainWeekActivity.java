package com.example.oskin.lesson_15_clean_architecture.Presentation.UI;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.ForecastDTOInput;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.ForecastDTOOutput;
import com.example.oskin.lesson_15_clean_architecture.Presentation.Presenters.IMainWeekView;
import com.example.oskin.lesson_15_clean_architecture.Presentation.Presenters.MainWeekPresenter;
import com.example.oskin.lesson_15_clean_architecture.R;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainWeekActivity extends AppCompatActivity implements IMainWeekView {

    private RecyclerView mRecyclerView;
    private ForecastAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private TextView mHumidity;
    private TextView mCloud;
    private TextView mWindSpeed;
    private TextView mTemperature;
    private TextView mFeelsLike;
    private TextView mPrecip;
    private TextView mConditionText;
    private TextView mCityName;
    private ImageView mConditionImg;
    private ProgressBar mProgressBar;

    private MainWeekPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_main);
        initViews();
        initRecycler();
        mPresenter = new MainWeekPresenter();
    }

    private void initRecycler() {
        mRecyclerView = findViewById(R.id.week_forecast_recycler);
        mLayoutManager = new LinearLayoutManager(
                this,
                RecyclerView.VERTICAL,
                false);
        mAdapter = new ForecastAdapter();
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initViews() {
        mHumidity = findViewById(R.id.week_current_humidity_text_view);
        mCloud = findViewById(R.id.week_current_cloud_text_view);
        mWindSpeed = findViewById(R.id.week_current_wind_speed_text_view);
        mTemperature = findViewById(R.id.week_current_temperature_text_view);
        mFeelsLike = findViewById(R.id.week_current_feels_like_text_view);
        mPrecip = findViewById(R.id.week_current_precipitation_text_view);
        mConditionText = findViewById(R.id.week_current_condition_text_view);
        mCityName = findViewById(R.id.week_current_city_name_text_view);

        mProgressBar = findViewById(R.id.week_progressBar);

        mConditionImg = findViewById(R.id.week_current_condition_img);
    }

    @Override
    protected void onResume() {
        mPresenter.onAttach(this);
        mPresenter.loadWeatherForecast();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mPresenter.onDetach();
        super.onPause();
    }

    @Override
    public void makeToast(String text) {
        Toast.makeText(MainWeekActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setCurrentDay(ForecastDTOOutput.Current currentDay,
                              ForecastDTOOutput.SettingPref settingPref) {

        mHumidity.setText(getResources().getString(R.string.humidity,
                Integer.toString(currentDay.getHumidity())));
        mCloud.setText(getResources().getString(R.string.cloud,
                Integer.toString(currentDay.getCloud())));
        mCityName.setText(currentDay.getCityName());
        mTemperature.setText(getResources().getString(R.string.temperature,
                Double.toString(currentDay.getTemp())));
        mFeelsLike.setText(getResources().getString(R.string.feels_like,
                Double.toString(currentDay.getFeelslike())));
        mConditionText.setText(currentDay.getConditionText());

        if (settingPref.isKilometers()) {
            mWindSpeed.setText(getResources().getString(R.string.wind_speed_in_kmh,
                    Double.toString(currentDay.getWind())));
        } else {
            mWindSpeed.setText(getResources().getString(R.string.wind_speed_in_mph,
                    Double.toString(currentDay.getWind())));
        }

        if (settingPref.isMm()) {
            mPrecip.setText(getResources().getString(R.string.precipiation_in_mm,
                    Double.toString(currentDay.getPrecip())));
        } else {
            mPrecip.setText(getResources().getString(R.string.precipiation_in_in,
                    Double.toString(currentDay.getPrecip())));
        }

        //TODO load img
    }

    @Override
    public void setDataIntoAdapter(List<ForecastDTOOutput.Day> dayList) {
        mAdapter.setData(dayList);
    }

    @Override
    public void starNewScreen() {
        //TODO day activity
    }
}
