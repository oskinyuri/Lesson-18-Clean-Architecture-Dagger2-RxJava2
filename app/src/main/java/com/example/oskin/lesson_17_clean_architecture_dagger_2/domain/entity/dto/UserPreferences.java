package com.example.oskin.lesson_17_clean_architecture_dagger_2.domain.entity.dto;

import java.util.Objects;

public class UserPreferences {
    private String mCityName;

    private int mCountDays;
    private boolean mIsKm;
    private boolean mIsCelsius;
    private boolean mIsMm;

    private float mLatitude;
    private float mLongitude;

    public UserPreferences(UserPreferences other) {
        this.mCityName = other.mCityName;
        this.mCountDays = other.mCountDays;
        this.mIsKm = other.mIsKm;
        this.mIsCelsius = other.mIsCelsius;
        this.mIsMm = other.mIsMm;
        this.mLatitude = other.mLatitude;
        this.mLongitude = other.mLongitude;
    }

    public UserPreferences() {
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

    public boolean isKm() {
        return mIsKm;
    }

    public void setKm(boolean km) {
        mIsKm = km;
    }

    public boolean isCelsius() {
        return mIsCelsius;
    }

    public void setCelsius(boolean celsius) {
        mIsCelsius = celsius;
    }

    public boolean isMm() {
        return mIsMm;
    }

    public void setMm(boolean mm) {
        mIsMm = mm;
    }

    public float getLatitude() {
        return mLatitude;
    }

    public void setLatitude(float latitude) {
        mLatitude = latitude;
    }

    public float getLongitude() {
        return mLongitude;
    }

    public void setLongitude(float longitude) {
        mLongitude = longitude;
    }

    public String getCityCoordinatesToString(){
        return (mLatitude + " , " + mLongitude);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserPreferences)) return false;
        UserPreferences that = (UserPreferences) o;
        return mCountDays == that.mCountDays &&
                mIsKm == that.mIsKm &&
                mIsCelsius == that.mIsCelsius &&
                mIsMm == that.mIsMm &&
                Float.compare(that.mLatitude, mLatitude) == 0 &&
                Float.compare(that.mLongitude, mLongitude) == 0 &&
                Objects.equals(mCityName, that.mCityName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mCityName, mCountDays, mIsKm, mIsCelsius, mIsMm, mLatitude, mLongitude);
    }
}
