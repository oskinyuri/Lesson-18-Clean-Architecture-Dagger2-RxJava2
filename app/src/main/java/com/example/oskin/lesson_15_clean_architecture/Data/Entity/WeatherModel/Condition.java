package com.example.oskin.lesson_15_clean_architecture.Data.Entity.WeatherModel;

import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;

public class Condition {

    private static final long serialVersionUID = 1L;

    @ColumnInfo
    @SerializedName("text")
    public String text;

    @ColumnInfo
    @SerializedName("icon")
    public String icon_url;

    @ColumnInfo
    @SerializedName("code")
    public int code;

    public Condition() {
    }

    public String getText() {
        return this.text;
    }

    public void setText(String mText) {
        this.text = mText;
    }

    public String getIcon_url() {
        return this.icon_url;
    }

    public void setIcon_url(String mIcon) {
        this.icon_url = mIcon;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int mCode) {
        this.code = mCode;
    }
}
