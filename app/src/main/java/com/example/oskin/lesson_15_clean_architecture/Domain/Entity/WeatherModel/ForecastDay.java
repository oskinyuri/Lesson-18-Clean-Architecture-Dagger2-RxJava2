package com.example.oskin.lesson_15_clean_architecture.Domain.Entity.WeatherModel;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ForecastDay {

    @PrimaryKey
    @NonNull
    @ColumnInfo
    @SerializedName("date_epoch")
    public long date_epoch;

    @ColumnInfo
    @SerializedName("date")
    public String date;

    @ColumnInfo(name = "day_of_week")
    public String dayOfWeek;

    @Embedded
    @SerializedName("day")
    public Day day;

    @ColumnInfo
    public String cityName;

    public long getDate_epoch() {
        return date_epoch;
    }

    public void setDate_epoch(@NonNull long date_epoch) {
        this.date_epoch = date_epoch;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
