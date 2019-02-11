package com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO;

import java.util.List;

public class ForecastDTOOutput {

    private Current mCurrent;
    private List<Day> mForecastForDayList;
    private SettingPref mSettingPref;

    public Current getCurrent() {
        return mCurrent;
    }

    public void setCurrent(Current current) {
        mCurrent = current;
    }

    public List<Day> getForecastForDayList() {
        return mForecastForDayList;
    }

    public void setForecastForDayList(List<Day> forecastForDayList) {
        mForecastForDayList = forecastForDayList;
    }

    public SettingPref getSettingPref() {
        return mSettingPref;
    }

    public void setSettingPref(SettingPref settingPref) {
        mSettingPref = settingPref;
    }

    public static class SettingPref {
        private boolean mIsCelsius = true;
        private boolean mIsKilometers = true;
        private boolean mIsMm = true;

        public void setCelsius(boolean celsius) {
            mIsCelsius = celsius;
        }

        public boolean isCelsius() {
            return mIsCelsius;
        }

        public boolean isKilometers() {
            return mIsKilometers;
        }

        public void setKilometers(boolean kilometers) {
            mIsKilometers = kilometers;
        }

        public boolean isMm() {
            return mIsMm;
        }

        public void setMm(boolean mm) {
            mIsMm = mm;
        }
    }

    public static class Day {
        private String mDate;
        private String mDayOfWeek;
        private String mConditionImgUrl;
        private String mConditionText;
        private int mDateEpoch;
        private double mMinTemp;
        private double mMaxTemp;
        private double mWind;
        private double mPrecip;

        public String getDate() {
            return mDate;
        }

        public void setDate(String date) {
            mDate = date;
        }

        public String getDayOfWeek() {
            return mDayOfWeek;
        }

        public void setDayOfWeek(String dayOfWeek) {
            mDayOfWeek = dayOfWeek;
        }

        public int getDateEpoch() {
            return mDateEpoch;
        }

        public void setDateEpoch(int dateEpoch) {
            mDateEpoch = dateEpoch;
        }

        public String getConditionImgUrl() {
            return mConditionImgUrl;
        }

        public void setConditionImgUrl(String conditionImgUrl) {
            mConditionImgUrl = conditionImgUrl;
        }

        public String getConditionText() {
            return mConditionText;
        }

        public void setConditionText(String conditionText) {
            mConditionText = conditionText;
        }

        public double getMinTemp() {
            return mMinTemp;
        }

        public void setMinTemp(double minTemp) {
            mMinTemp = minTemp;
        }

        public double getMaxTemp() {
            return mMaxTemp;
        }

        public void setMaxTemp(double maxTemp) {
            mMaxTemp = maxTemp;
        }

        public double getWind() {
            return mWind;
        }

        public void setWind(double wind) {
            mWind = wind;
        }

        public double getPrecip() {
            return mPrecip;
        }

        public void setPrecip(double precip) {
            mPrecip = precip;
        }
    }

    public static class Current {
        private int mHumidity;
        private int mCloud;
        private double mWind;
        private double mTemp;
        private double mFeelslike;
        private double mPrecip;
        private String mConditionText;
        private String mConditionImgUrl;
        private String cityName;

        public int getHumidity() {
            return mHumidity;
        }

        public void setHumidity(int humidity) {
            mHumidity = humidity;
        }

        public int getCloud() {
            return mCloud;
        }

        public void setCloud(int cloud) {
            mCloud = cloud;
        }

        public double getWind() {
            return mWind;
        }

        public void setWind(double wind) {
            mWind = wind;
        }

        public double getTemp() {
            return mTemp;
        }

        public void setTemp(double temp) {
            mTemp = temp;
        }

        public double getFeelslike() {
            return mFeelslike;
        }

        public void setFeelslike(double feelslike) {
            mFeelslike = feelslike;
        }

        public double getPrecip() {
            return mPrecip;
        }

        public void setPrecip(double precip) {
            mPrecip = precip;
        }

        public String getConditionText() {
            return mConditionText;
        }

        public void setConditionText(String conditionText) {
            mConditionText = conditionText;
        }

        public String getConditionImgUrl() {
            return mConditionImgUrl;
        }

        public void setConditionImgUrl(String conditionImgUrl) {
            mConditionImgUrl = conditionImgUrl;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }
    }
}
