package com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO;

public class ForecastDTOInput {

    private String mCityName;
    private int mCountDays;

    public ForecastDTOInput() {
    }

    public ForecastDTOInput(String cityName, int countDays) {
        mCityName = cityName;
        mCountDays = countDays;
    }

    public String getCityName() {
        return mCityName;
    }

    public void setCityName(String cityName) {
        mCityName = cityName;
    }

    public int getCountDays() {
        return mCountDays;
    }

    public void setCountDays(int countDays) {
        mCountDays = countDays;
    }
}