package com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.Entity.WeatherModel;

import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

public class Day {

    private static final long serialVersionUID = 1L;
    @ColumnInfo
    @SerializedName("maxtemp_c")
    public double maxtemp_c;
    @ColumnInfo
    @SerializedName("maxtemp_f")
    public double maxtemp_f;
    @ColumnInfo
    @SerializedName("mintemp_c")
    public double mintemp_c;
    @ColumnInfo
    @SerializedName("mintemp_f")
    public double mintemp_f;
    @ColumnInfo
    @SerializedName("avgtemp_c")
    public double avgtemp_c;
    @ColumnInfo
    @SerializedName("avgtemp_f")
    public double avgtemp_f;
    @ColumnInfo
    @SerializedName("maxwind_mph")
    public double maxwind_mph;
    @ColumnInfo
    @SerializedName("maxwind_kph")
    public double maxwind_kph;
    @ColumnInfo
    @SerializedName("totalprecip_mm")
    public double totalprecip_mm;
    @ColumnInfo
    @SerializedName("totalprecip_in")
    public double totalprecip_in;
    @Embedded (prefix = "condition")
    @SerializedName("condition")
    public Condition condition = new Condition();

    public Day() {
    }

    public double getMaxtempC() {
        return this.maxtemp_c;
    }

    public void setMaxtempC(double mMaxtemp_c) {
        this.maxtemp_c = mMaxtemp_c;
    }

    public double getMaxtempF() {
        return this.maxtemp_f;
    }

    public void setMaxtempF(double mMaxtemp_f) {
        this.maxtemp_f = mMaxtemp_f;
    }

    public double getMintempC() {
        return this.mintemp_c;
    }

    public void setMintempC(double mMintemp_c) {
        this.mintemp_c = mMintemp_c;
    }

    public double getMintempF() {
        return this.mintemp_f;
    }

    public void setMintempF(double mMintemp_f) {
        this.mintemp_f = mMintemp_f;
    }

    public double getAvgtempC() {
        return this.avgtemp_c;
    }

    public void setAvgtempC(double mAvgtemp_c) {
        this.avgtemp_c = mAvgtemp_c;
    }

    public double getAvgtempF() {
        return this.avgtemp_f;
    }

    public void setAvgtempF(double mAvgtemp_f) {
        this.avgtemp_f = mAvgtemp_f;
    }

    public double getMaxwindMph() {
        return this.maxwind_mph;
    }

    public void setMaxwindMph(double mMaxwind_mph) {
        this.maxwind_mph = mMaxwind_mph;
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
        return this.condition;
    }

    public void setCondition(Condition mCondition) {
        this.condition = mCondition;
    }
}
