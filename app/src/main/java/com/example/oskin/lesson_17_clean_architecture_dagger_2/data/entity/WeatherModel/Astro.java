package com.example.oskin.lesson_17_clean_architecture_dagger_2.data.entity.WeatherModel;

import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;

public class Astro {

    private static final long serialVersionUID = 1L;
    @ColumnInfo
    @SerializedName("sunrise")
    public String sunrise;
    @ColumnInfo
    @SerializedName("sunset")
    public String sunset;
    @ColumnInfo
    @SerializedName("moonrise")
    public String moonrise;
    @ColumnInfo
    @SerializedName("moonset")
    public String moonset;

    public Astro() {
    }

    public String getSunrise() {
        return this.sunrise;
    }

    public void setSunrise(String mSunrise) {
        this.sunrise = mSunrise;
    }

    public String getSunset() {
        return this.sunset;
    }

    public void setSunset(String mSunset) {
        this.sunset = mSunset;
    }

    public String getMoonrise() {
        return this.moonrise;
    }

    public void setMoonrise(String mMoonrise) {
        this.moonrise = mMoonrise;
    }

    public String getMoonset() {
        return this.moonset;
    }

    public void setMoonset(String mMoonset) {
        this.moonset = mMoonset;
    }

}
