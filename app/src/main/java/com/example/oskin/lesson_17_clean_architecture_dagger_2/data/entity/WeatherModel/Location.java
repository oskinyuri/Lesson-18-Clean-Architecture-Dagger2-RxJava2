package com.example.oskin.lesson_17_clean_architecture_dagger_2.data.entity.WeatherModel;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

public class Location {

    private static final long serialVersionUID = 1L;
    @ColumnInfo
    @NonNull
    @SerializedName("name")
    public String name;
    @ColumnInfo
    @NonNull
    @SerializedName("region")
    public String region;
    @ColumnInfo
    @NonNull
    @SerializedName("country")
    public String country;
    @ColumnInfo
    @NonNull
    @SerializedName("tz_id")
    public String tz_id;
    @ColumnInfo
    @NonNull
    @SerializedName("localtime")
    public String localtime;
    @ColumnInfo
    @NonNull
    @SerializedName("lat")
    public double lat;
    @ColumnInfo
    @NonNull
    @SerializedName("lon")
    public double lon;
    @ColumnInfo
    @NonNull
    @SerializedName("localtime_epoch")
    public int localtime_epoch;

    public Location() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String mName) {
        this.name = mName;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String mRegion) {
        this.region = mRegion;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String mCountry) {
        this.country = mCountry;
    }

    public double getLat() {
        return this.lat;
    }

    public void setLat(double mLat) {
        this.lat = mLat;
    }

    public double getLong() {
        return this.lon;
    }

    public void setLong(double mLong) {
        this.lon = mLong;
    }

    public String getTzId() {
        return this.tz_id;
    }

    public void setTzId(String mTz_id) {
        this.tz_id = mTz_id;
    }

    public int getLocaltimeEpoch() {
        return this.localtime_epoch;
    }

    public void setLocaltimeEpoch(int mLocaltimeEpoch) {
        this.localtime_epoch = mLocaltimeEpoch;
    }

    public String getLocaltime() {
        return this.localtime;
    }

    public void setLocaltimeEpoch(String mLocaltime) {
        this.localtime = mLocaltime;
    }

}
