package com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.Entity.WeatherModel;

import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

public class ForecastDay {

    private static final long serialVersionUID = 1L;
    @SerializedName("date")
    public String date;
    @SerializedName("date_epoch")
    public int date_epoch;
    @Embedded (prefix = "day_")
    @SerializedName("day")
    Day day = new Day();
    @Embedded (prefix = "astro_")
    @SerializedName("astro")
    Astro astro = new Astro();
    @ColumnInfo(name = "day_of_week")
    public String dayOfWeek;

    public ForecastDay() {
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String mDate) {
        this.date = mDate;
    }

    public int getDateEpoch() {
        return this.date_epoch;
    }

    public void setDateEpoch(int mDateEpoch) {
        this.date_epoch = mDateEpoch;
    }

    public Day getDay() {
        return this.day;
    }

    public void setDay(Day mDay) {
        this.day = mDay;
    }

    public Astro getAstro() {
        return this.astro;
    }

    public void setAstro(Astro mAstro) {
        this.astro = mAstro;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

}
