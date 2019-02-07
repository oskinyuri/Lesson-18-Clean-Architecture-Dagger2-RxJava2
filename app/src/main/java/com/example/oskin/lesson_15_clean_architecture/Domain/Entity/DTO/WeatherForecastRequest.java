package com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO;

import android.os.Parcel;
import android.os.Parcelable;

public class WeatherForecastRequest implements Parcelable {

    private String mCityName;
    private int mCountDays;

    public WeatherForecastRequest() {
    }

    public WeatherForecastRequest(String cityName, int countDays) {
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

    protected WeatherForecastRequest(Parcel in) {
        mCityName = in.readString();
        mCountDays = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mCityName);
        dest.writeInt(mCountDays);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<WeatherForecastRequest> CREATOR = new Parcelable.Creator<WeatherForecastRequest>() {
        @Override
        public WeatherForecastRequest createFromParcel(Parcel in) {
            return new WeatherForecastRequest(in);
        }

        @Override
        public WeatherForecastRequest[] newArray(int size) {
            return new WeatherForecastRequest[size];
        }
    };
}