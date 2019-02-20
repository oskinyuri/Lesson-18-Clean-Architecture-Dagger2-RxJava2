package com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.Entity.WeatherModel;

import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

public class Current {

    private static final long serialVersionUID = 1L;
    @ColumnInfo
    @SerializedName("last_updated_epoch")
    public int last_updated_epoch;
    @ColumnInfo
    @SerializedName("wind_degree")
    public int wind_degree;
    @ColumnInfo
    @SerializedName("humidity")
    public int humidity;
    @ColumnInfo
    @SerializedName("cloud")
    public int cloud;
    @ColumnInfo
    @SerializedName("last_updated")
    public String last_updated;
    @ColumnInfo
    @SerializedName("wind_dir")
    public String wind_dir;
    @ColumnInfo
    @SerializedName("temp_c")
    public double temp_c;
    @ColumnInfo
    @SerializedName("temp_f")
    public double temp_f;
    @ColumnInfo
    @SerializedName("wind_mph")
    public double wind_mph;
    @ColumnInfo
    @SerializedName("wind_kph")
    public double wind_kph;
    @ColumnInfo
    @SerializedName("pressure_mb")
    public double pressure_mb;
    @ColumnInfo
    @SerializedName("pressure_in")
    public double pressure_in;
    @ColumnInfo
    @SerializedName("precip_mm")
    public double precip_mm;
    @ColumnInfo
    @SerializedName("precip_in")
    public double precip_in;
    @ColumnInfo
    @SerializedName("feelslike_c")
    public double feelslike_c;
    @ColumnInfo
    @SerializedName("feelslike_f")
    public double feelslike_f;
    @Embedded (prefix = "condition")
    @SerializedName("condition")
    public Condition condition = new Condition();

    public Current() {
    }

    public int getLastUpdateEpoch() {
        return this.last_updated_epoch;
    }

    public void setLastUpdateEpoch(int mLastUpdateEpoch) {
        this.last_updated_epoch = mLastUpdateEpoch;
    }

    public String getLastUpdated() {
        return this.last_updated;
    }

    public void setLastUpdated(String mLastUpdated) {
        this.last_updated = mLastUpdated;
    }

    public double getTempC() {
        return this.temp_c;
    }

    public void setTempC(Double mTempC) {
        this.temp_c = mTempC;
    }

    public double getTempF() {
        return this.temp_f;
    }

    public void setTempF(Double mTempF) {
        this.temp_f = mTempF;
    }

    public Condition getCondition() {
        return this.condition;
    }

    public void setCondition(Condition mCondition) {
        this.condition = mCondition;
    }

    public double getWindMph() {
        return this.wind_mph;
    }

    public void setWindMph(double mWindMph) {
        this.wind_mph = mWindMph;
    }

    public double getWindKph() {
        return this.wind_kph;
    }

    public void setWindKph(double mWindKph) {
        this.wind_kph = mWindKph;
    }

    public int getWindDegree() {
        return this.wind_degree;
    }

    public void setWindDegree(int mWindDegree) {
        this.wind_degree = mWindDegree;
    }

    public String getWindDir() {
        return this.wind_dir;
    }

    public void setWindDir(String mWindDir) {
        this.wind_dir = mWindDir;
    }

    public double getPressureMb() {
        return this.pressure_mb;
    }

    public void setPressureMb(double mPressureMb) {
        this.pressure_mb = mPressureMb;
    }

    public double getPressureIn() {
        return this.pressure_in;
    }

    public void setPressureIn(double mPressureIn) {
        this.pressure_in = mPressureIn;
    }

    public double getPrecipMm() {
        return this.precip_mm;
    }

    public void setPrecipMm(double mPrecipMm) {
        this.precip_mm = mPrecipMm;
    }

    public double getPrecipIn() {
        return this.precip_in;
    }

    public void setPrecipIn(double mPrecipIn) {
        this.precip_in = mPrecipIn;
    }

    public int getHumidity() {
        return this.humidity;
    }

    public void setHumidity(int mHumidity) {
        this.humidity = mHumidity;
    }

    public int getCloud() {
        return this.cloud;
    }

    public void setCloud(int mCloud) {
        this.cloud = mCloud;
    }

    public double getFeelslikeC() {
        return this.feelslike_c;
    }

    public void setFeelslikeC(double mFeelslikeC) {
        this.feelslike_c = mFeelslikeC;
    }

    public double getFeelslikeF() {
        return this.feelslike_f;
    }

    public void setFeelslikeF(double mFeelslikeF) {
        this.feelslike_f = mFeelslikeF;
    }

}
