package com.example.oskin.lesson_17_clean_architecture_dagger_2.Data.Entity.WeatherModel;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class WeatherModel {

    private static final long serialVersionUID = 1L;

    /**
     * Нужен зесь только потому что Room не может взять первичный ключ из вложенного объекта.
     * Сетится в Database Manager'e используещейся базы даных.
     */
    @PrimaryKey
    @NonNull
    public String cityName;

    @Embedded(prefix = "location_")
    @SerializedName("location")
    public Location location;
    @Embedded(prefix = "current_")
    @SerializedName("current")
    public Current current;
    @Embedded(prefix = "forecast_")
    @SerializedName("forecast")
    public Forecast forecast;

    public WeatherModel() {
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location mLocation) {
        this.location = mLocation;
    }

    public Current getCurrent() {
        return this.current;
    }

    public void setCurrent(Current mCurrent) {
        this.current = mCurrent;
    }

    public Forecast getForecast() {
        return this.forecast;
    }

    public void setForecast(Forecast mForecast) {
        this.forecast = mForecast;
    }

    @NonNull
    public String getCityName() {
        return cityName;
    }

    public void setCityName(@NonNull String cityName) {
        this.cityName = cityName;
    }
}
