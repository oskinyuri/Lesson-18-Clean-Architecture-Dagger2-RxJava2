package com.example.oskin.lesson_15_clean_architecture.Presentation.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.WeatherForecastRequest;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.WeatherModel.ForecastDay;
import com.example.oskin.lesson_15_clean_architecture.Presentation.Presenters.ForecastPresenter;
import com.example.oskin.lesson_15_clean_architecture.Presentation.Presenters.IForecastView;
import com.example.oskin.lesson_15_clean_architecture.R;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ForecastActivity extends AppCompatActivity implements IForecastView {

    private RecyclerView mRecyclerView;
    private ForecastAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ForecastPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        initRecycler();
        mPresenter = new ForecastPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onAttach(this);
        mPresenter.setRequest( (WeatherForecastRequest) getIntent()
                .getParcelableExtra(ForecastPresenter.REQUEST_KEY));
    }

    @Override
    protected void onPause() {
        mPresenter.onDetach();
        super.onPause();
    }

    private void initRecycler() {
        mRecyclerView = findViewById(R.id.forecast_recycler);
        mLayoutManager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL,false);
        mAdapter = new ForecastAdapter();

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void setForecast(List<ForecastDay> forecastDayList) {
        mAdapter.setData(forecastDayList);
    }

    @Override
    public void makeToast(String text) {

    }

    @Override
    public void startProgress() {

    }

    @Override
    public void hideProgress() {

    }

    public static Intent newIntent(Context context, WeatherForecastRequest request){
        Intent intent = new Intent(context, ForecastActivity.class);
        intent.putExtra(ForecastPresenter.REQUEST_KEY,request);
        return intent;
    }
}
