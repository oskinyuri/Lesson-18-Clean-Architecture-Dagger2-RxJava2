package com.example.oskin.lesson_15_clean_architecture.Data.Entity.DTO;

public class LastRequestInfo {

    private long lastTimeInEpoch;
    private String lastCityName;
    private int lastCountDays;

    public long getLastTimeInEpoch() {
        return lastTimeInEpoch;
    }

    public void setLastTimeInEpoch(long lastTimeInEpoch) {
        this.lastTimeInEpoch = lastTimeInEpoch;
    }

    public String getLastCityName() {
        return lastCityName;
    }

    public void setLastCityName(String lastCityName) {
        this.lastCityName = lastCityName;
    }

    public int getLastCountDays() {
        return lastCountDays;
    }

    public void setLastCountDays(int lastCountDays) {
        this.lastCountDays = lastCountDays;
    }
}
