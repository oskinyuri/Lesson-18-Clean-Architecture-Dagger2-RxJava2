package com.example.oskin.lesson_15_clean_architecture.Presentation.UI;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.WeatherForecastRequest;
import com.example.oskin.lesson_15_clean_architecture.Presentation.Presenters.IMainView;
import com.example.oskin.lesson_15_clean_architecture.Presentation.Presenters.MainPresenter;
import com.example.oskin.lesson_15_clean_architecture.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements IMainView {

    private TextView mTextView;
    private EditText mCityName;
    private EditText mCountDay;
    private Button mLoadForecast;
    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.textView);
        mCityName = findViewById(R.id.main_act_city_name);
        mCountDay = findViewById(R.id.main_act_count_days);
        mLoadForecast = findViewById(R.id.main_act_load_btn);
        mLoadForecast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.loadWeatherForecast(mCityName.getText().toString(),
                        Integer.parseInt(mCountDay.getText().toString()));
                //TODO start progress bar in presenter
            }
        });
        mPresenter = new MainPresenter();
    }

    @Override
    protected void onResume() {
        mPresenter.onAttach(this);
        super.onResume();
    }

    @Override
    protected void onPause() {
        mPresenter.onDetach();
        super.onPause();
    }

    @Override
    public void setCountDays(int countDays) {
        mCountDay.setText(String.valueOf(countDays));
    }

    @Override
    public void setCityName(String cityName) {
        mCityName.setText(cityName);
    }


    @Override
    public void makeToast(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void starNewScreen(WeatherForecastRequest request) {
        startActivity(ForecastActivity.newIntent(MainActivity.this, request));
    }
}
