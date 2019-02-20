package com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.UI;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.UserPreferences;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.DI.Components.DaggerSettingActivityComponent;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.DI.Components.SettingActivityComponent;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.DI.Modules.activity.SettingActivityModule;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.Presenters.ISettingView;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.Presenters.SettingPresenter;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.R;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.WeatherApp;

import javax.inject.Inject;

public class SettingsActivity extends AppCompatActivity implements ISettingView {

    private Switch mTempSwitch;
    private Switch mWindSwitch;
    private Switch mPrecipSwitch;
    private Spinner mCountDaysSpinner;
    private EditText mCityNameET;
    private Button mSaveCityNameBtn;

    private UserPreferences mUserPreferences;
    private SettingActivityComponent mComponent;

    @Inject
    SettingPresenter mPresenter;

    private Integer[] mCountDays;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initViews();
        initActionBar();

        mComponent = DaggerSettingActivityComponent.builder()
                .appComponent(WeatherApp.getInstance().getAppComponent())
                .settingActivityModule(new SettingActivityModule())
                .build();
        mComponent.injectActivity(SettingsActivity.this);
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
        mCityNameET = findViewById(R.id.settings_city_name_edit_text);
        mSaveCityNameBtn = findViewById(R.id.settings_save_city_name_btn);
        mCountDaysSpinner = findViewById(R.id.settings_spinner_count_days);
    }

    @Override
    public void setSpinnerAdapter(Integer[] data){
        mCountDays = data;
        //TODO а это тоже надо через Dagger
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mCountDays);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCountDaysSpinner.setAdapter(adapter);

    }

    private void initListeners() {
        mSaveCityNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO проверку на не нулевое поле
                mUserPreferences.setCityName(mCityNameET.getText().toString());
                mPresenter.SetUserPrefSettings(mUserPreferences);
            }
        });
        mTempSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mUserPreferences.setCelsius(isChecked);
                mPresenter.SetUserPrefSettings(mUserPreferences);
            }
        });
        mWindSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mUserPreferences.setKm(isChecked);
                mPresenter.SetUserPrefSettings(mUserPreferences);
            }
        });
        mPrecipSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mUserPreferences.setMm(isChecked);
                mPresenter.SetUserPrefSettings(mUserPreferences);
            }
        });
        mCountDaysSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (mCountDaysSpinner.getTag() != (Integer) position) {
                    mUserPreferences.setCountDays(mCountDays[position]);
                    mPresenter.SetUserPrefSettings(mUserPreferences);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onAttach(this);
        mPresenter.initSpinnerAdapter();
        mPresenter.getUserPrefSettings();
    }

    private void initState() {
        mCityNameET.setText(mUserPreferences.getCityName());
        mTempSwitch.setChecked(mUserPreferences.isCelsius());
        mPrecipSwitch.setChecked(mUserPreferences.isMm());
        mWindSwitch.setChecked(mUserPreferences.isKm());
        mCountDaysSpinner.setSelection(mUserPreferences.getCountDays()-1);
        mCountDaysSpinner.setTag(mUserPreferences.getCountDays()-1);
        initListeners();
    }

    @Override
    protected void onPause() {
        mPresenter.onDetach();
        super.onPause();
    }

    @Override
    public void setUserPref(UserPreferences userPreferences) {
        mUserPreferences = userPreferences;
        initState();
    }

    @Override
    public void makeToast(String text) {
        Toast.makeText(SettingsActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    public static Intent newIntent(Context context){
        return new Intent(context, SettingsActivity.class);
    }
}
