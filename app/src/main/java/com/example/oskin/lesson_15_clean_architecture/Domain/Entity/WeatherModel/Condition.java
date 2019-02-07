package com.example.oskin.lesson_15_clean_architecture.Domain.Entity.WeatherModel;

import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;

public class Condition {

    @ColumnInfo
    @SerializedName("text")
    public String text;

    @ColumnInfo
    @SerializedName("icon")
    public String icon_url;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }
}
