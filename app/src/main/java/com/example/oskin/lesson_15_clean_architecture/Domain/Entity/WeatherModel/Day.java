package com.example.oskin.lesson_15_clean_architecture.Domain.Entity.WeatherModel;

import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;

@Entity
public class Day {

    @ColumnInfo
    @SerializedName("maxtemp_c")
    public double maxtemp_c;

    @ColumnInfo
    @SerializedName("mintemp_c")
    public double mintemp_c;

    @ColumnInfo
    @SerializedName("avgtemp_c")
    public double avgtemp_c;

    @ColumnInfo
    @SerializedName("maxwind_kph")
    public double maxwind_kph;

    @ColumnInfo
    @SerializedName("totalprecip_mm")
    public double totalprecip_mm;

    @ColumnInfo
    @SerializedName("totalprecip_in")
    public double totalprecip_in;

    @Embedded
    @SerializedName("condition")
    public Condition condition;

    public Day() {
    }

    public double getMaxtempC() {
        return this.maxtemp_c;
    }

    public void setMaxtempC(double mMaxtemp_c) {
        this.maxtemp_c = mMaxtemp_c;
    }

    public double getMintempC() {
        return this.mintemp_c;
    }

    public void setMintempC(double mMintemp_c) {
        this.mintemp_c = mMintemp_c;
    }

    public double getAvgtempC() {
        return this.avgtemp_c;
    }

    public void setAvgtempC(double mAvgtemp_c) {
        this.avgtemp_c = mAvgtemp_c;
    }

    public double getMaxwindKph() {
        return this.maxwind_kph;
    }

    public void setMaxwindKph(double mMaxwind_kph) {
        this.maxwind_kph = mMaxwind_kph;
    }

    public double getTotalprecipMm() {
        return this.totalprecip_mm;
    }

    public void setTotalprecipMm(double mTotalprecip_mm) {
        this.totalprecip_mm = mTotalprecip_mm;
    }

    public double getTotalprecipIn() {
        return this.totalprecip_in;
    }

    public void setTotalprecipIn(double mTotalprecip_in) {
        this.totalprecip_in = mTotalprecip_in;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition= condition;
    }
}
