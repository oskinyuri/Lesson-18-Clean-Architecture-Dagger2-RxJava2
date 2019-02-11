package com.example.oskin.lesson_15_clean_architecture.Data.Repositories;

import com.example.oskin.lesson_15_clean_architecture.Data.Entity.WeatherModel.ForecastDay;
import com.example.oskin.lesson_15_clean_architecture.Data.Entity.WeatherModel.WeatherModel;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.ForecastDTOOutput;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.SharedPrefDTO;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.Utils.UtilDate;

import java.util.ArrayList;
import java.util.List;

public class WeatherMapper {

    public ForecastDTOOutput getDTOFromPOJO(WeatherModel weatherModel, SharedPrefDTO sharedPrefDTO) {

        ForecastDTOOutput dtoOutput = new ForecastDTOOutput();

        /**
         * Сетинг текущих настроек
         */
        ForecastDTOOutput.SettingPref settingPref = new ForecastDTOOutput.SettingPref();
        settingPref.setCelsius(sharedPrefDTO.isCelsius());
        settingPref.setMm(sharedPrefDTO.isMmHg());
        settingPref.setKilometers(sharedPrefDTO.isKm());
        dtoOutput.setSettingPref(settingPref);

        /**
         * Сетинг текущего дня
         * Выбор данных в зависимости от отединиц измерения в sheredPref'ов
         */

        ForecastDTOOutput.Current current = new ForecastDTOOutput.Current();

        current.setCityName(weatherModel.getLocation().getName());
        current.setConditionText(weatherModel.getCurrent().getCondition().getText());
        current.setConditionImgUrl(weatherModel.getCurrent().getCondition().getIcon_url());
        current.setHumidity(weatherModel.getCurrent().getHumidity());
        current.setCloud(weatherModel.getCurrent().getCloud());

        if (sharedPrefDTO.isCelsius()) {
            current.setTemp(weatherModel.getCurrent().getTempC());
            current.setFeelslike(weatherModel.getCurrent().getFeelslikeC());
        } else {
            current.setTemp(weatherModel.getCurrent().getTempF());
            current.setFeelslike(weatherModel.getCurrent().getFeelslikeF());
        }
        if (sharedPrefDTO.isKm()) {
            current.setWind(weatherModel.getCurrent().getWindKph());
        } else {
            current.setWind(weatherModel.getCurrent().getWindMph());
        }
        if (sharedPrefDTO.isMmHg()){
            current.setPrecip(weatherModel.getCurrent().getPrecipMm());
        } else {
            current.setPrecip(weatherModel.getCurrent().getPrecipIn());
        }
        dtoOutput.setCurrent(current);


        /**
         * Сетинг дней на неделю
         */

        List<ForecastDTOOutput.Day> dayList = new ArrayList<>();
        for (ForecastDay fDay : weatherModel.getForecast().getForecastDayList()) {
            ForecastDTOOutput.Day day = new ForecastDTOOutput.Day();
            day.setDate(fDay.getDate());
            day.setDateEpoch(fDay.getDateEpoch());

            if (fDay.getDateEpoch() == UtilDate.getTodayEpoch()){
                /**
                 * Поменть название дня недели на "TODAY"
                 */
                day.setDayOfWeek(UtilDate.getTodayString());
            } else {
                day.setDayOfWeek(fDay.getDayOfWeek());
            }

            day.setConditionText(fDay.getDay().getCondition().getText());
            day.setConditionImgUrl(fDay.getDay().getCondition().getIcon_url());

            /**
             * Выбор данных в зависимости от отединиц измерения в sheredPref'ов
             */
            if (sharedPrefDTO.isCelsius()) {
                day.setMinTemp(fDay.getDay().getMintempC());
                day.setMaxTemp(fDay.getDay().getMaxtempC());
            } else {
                day.setMinTemp(fDay.getDay().getMintempF());
                day.setMaxTemp(fDay.getDay().getMaxtempF());
            }

            if (sharedPrefDTO.isKm()) {
                day.setWind(fDay.getDay().getMaxwindKph());
            } else {
                day.setWind(fDay.getDay().getMaxwindMph());
            }

            if (sharedPrefDTO.isMmHg()) {
                day.setPrecip(fDay.getDay().getTotalprecipMm());
            } else {
                day.setPrecip(fDay.getDay().getTotalprecipIn());
            }
            dayList.add(day);
        }
        dtoOutput.setForecastForDayList(dayList);

        return dtoOutput;
    }
}
