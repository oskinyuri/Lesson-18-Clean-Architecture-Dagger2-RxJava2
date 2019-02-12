package com.example.oskin.lesson_15_clean_architecture.Presentation.UI;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.SharedPrefDTO;
import com.example.oskin.lesson_15_clean_architecture.Presentation.Presenters.ISettingView;
import com.example.oskin.lesson_15_clean_architecture.Presentation.Presenters.SettingPresenter;
import com.example.oskin.lesson_15_clean_architecture.R;

public class SettingsActivity extends AppCompatActivity implements ISettingView {

    private Switch mTempSwitch;
    private Switch mWindSwitch;
    private Switch mPrecipSwitch;
    private Spinner mCitiesSpinner;
    private Spinner mCountDaysSpenner;

    private SharedPrefDTO mSharedPrefDTO;

    private SettingPresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initViews();
        initActionBar();
        initListeners();
        mPresenter = new SettingPresenter();
    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(getString(R.string.settings));
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }

    private void initViews() {
        mTempSwitch = findViewById(R.id.settings_switch_temp);
        mWindSwitch = findViewById(R.id.settings_switch_speed);
        mPrecipSwitch = findViewById(R.id.settings_switch_precip);
        mCitiesSpinner = findViewById(R.id.settings_spinner_city);
        mCountDaysSpenner = findViewById(R.id.settings_spinner_count_days);
    }

    private void initListeners() {
        mTempSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onAttach(this);
        mPresenter.getSharedPrefSettings();
    }

    @Override
    protected void onPause() {
        mPresenter.onDetach();
        super.onPause();
    }

    public static Intent newIntent(Context context){
        return new Intent(context, SettingsActivity.class);
    }

    @Override
    public void setSettingsSharedPref(SharedPrefDTO sharedPrefDTO) {
        mSharedPrefDTO = sharedPrefDTO;
    }

    @Override
    public void setCitiesDropList() {

    }

    @Override
    public void setCountDaysDropList() {

    }

    @Override
    public void makeToast(String text) {
        Toast.makeText(SettingsActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}
