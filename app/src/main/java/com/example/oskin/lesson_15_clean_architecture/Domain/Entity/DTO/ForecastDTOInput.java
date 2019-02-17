package com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO;

public class ForecastDTOInput {

    private double mLatitude;
    private double mLongitude;
    private int mCountDays;

    public ForecastDTOInput() {
    }

    public ForecastDTOInput(double latitude, double longitude, int countDays) {
        mLatitude = latitude;
        mLongitude = longitude;
        mCountDays = countDays;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double latitude) {
        mLatitude = latitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double longitude) {
        mLongitude = longitude;
    }

    public int getCountDays() {
        return mCountDays;
    }

    public void setCountDays(int countDays) {
        mCountDays = countDays;
    }
}