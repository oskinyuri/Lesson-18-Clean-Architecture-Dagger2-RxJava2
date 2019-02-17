package com.example.oskin.lesson_15_clean_architecture.Presentation.UI;

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

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.SharedPrefDTO;
import com.example.oskin.lesson_15_clean_architecture.Presentation.Presenters.ISettingView;
import com.example.oskin.lesson_15_clean_architecture.Presentation.Presenters.SettingPresenter;
import com.example.oskin.lesson_15_clean_architecture.R;

public class SettingsActivity extends AppCompatActivity implements ISettingView {

    private Switch mTempSwitch;
    private Switch mWindSwitch;
    private Switch mPrecipSwitch;
    private Spinner mCountDaysSpinner;
    private EditText mCityNameET;
    private Button mSaveCityNameBtn;

    private SharedPrefDTO mSharedPrefDTO;

    private SettingPresenter mPresenter;

    private Integer[] mCountDays;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initViews();
        initActionBar();
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
        mCityNameET = findViewById(R.id.settings_city_name_edit_text);
        mSaveCityNameBtn = findViewById(R.id.settings_save_city_name_btn);
        mCountDaysSpinner = findViewById(R.id.settings_spinner_count_days);
    }

    @Override
    public void setSpinnerAdapter(Integer[] data){
        mCountDays = data;
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mCountDays);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCountDaysSpinner.setAdapter(adapter);

    }

    private void initListeners() {
        mSaveCityNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO проверку на не нулевое поле
                mSharedPrefDTO.setCityName(mCityNameET.getText().toString());
                mPresenter.loadSharedPrefSettings(mSharedPrefDTO);
            }
        });
        mTempSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mSharedPrefDTO.setCelsius(isChecked);
                mPresenter.loadSharedPrefSettings(mSharedPrefDTO);
            }
        });
        mWindSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mSharedPrefDTO.setKm(isChecked);
                mPresenter.loadSharedPrefSettings(mSharedPrefDTO);
            }
        });
        mPrecipSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mSharedPrefDTO.setMm(isChecked);
                mPresenter.loadSharedPrefSettings(mSharedPrefDTO);
            }
        });
        mCountDaysSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (mCountDaysSpinner.getTag() != (Integer) position) {
                    mSharedPrefDTO.setCountDays(mCountDays[position]);
                    mPresenter.loadSharedPrefSettings(mSharedPrefDTO);
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
        mPresenter.getSharedPrefSettings();
    }

    private void initState() {
        mCityNameET.setText(mSharedPrefDTO.getCityName());
        mTempSwitch.setChecked(mSharedPrefDTO.isCelsius());
        mPrecipSwitch.setChecked(mSharedPrefDTO.isMm());
        mWindSwitch.setChecked(mSharedPrefDTO.isKm());
        mCountDaysSpinner.setSelection(mSharedPrefDTO.getCountDays()-1);
        mCountDaysSpinner.setTag(mSharedPrefDTO.getCountDays()-1);
        initListeners();
    }

    @Override
    protected void onPause() {
        mPresenter.onDetach();
        super.onPause();
    }

    @Override
    public void setSettingsSharedPref(SharedPrefDTO sharedPrefDTO) {
        mSharedPrefDTO = sharedPrefDTO;
        initState();
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

    public static Intent newIntent(Context context){
        return new Intent(context, SettingsActivity.class);
    }
}
