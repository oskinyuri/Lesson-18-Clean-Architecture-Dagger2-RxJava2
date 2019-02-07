package com.example.oskin.lesson_15_clean_architecture.Domain.Entity.Utils;

import java.time.DateTimeException;
import java.util.Calendar;


public class UtilDate {

    public static long getTodayEpoch(){
        long millisInDay = 60 * 60 * 24 * 1000;
        return ((System.currentTimeMillis() / millisInDay) * millisInDay) / 1000;
    }

    public static String getDayOfWeek(long epoch){
        long timeInMilliseconds = epoch * 1000;
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(timeInMilliseconds);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        return DayOfWeek.getDayOfInt(dayOfWeek);
    }

    private enum DayOfWeek{
        SUNDAY,
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY;


        private static DayOfWeek[] DAYS = DayOfWeek.values();

        public static String getDayOfInt(int day){

            return DAYS[day-1].name();
        }
    }
}
