package com.example.oskin.lesson_15_clean_architecture.Data.Repositories.WeatherRepository;

import com.example.oskin.lesson_15_clean_architecture.Data.Entity.WeatherModel.ForecastDay;
import com.example.oskin.lesson_15_clean_architecture.Data.Entity.WeatherModel.WeatherModel;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.ForecastDTOOutput;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.UserPreferences;
import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.Utils.UtilDate;

import java.util.ArrayList;
import java.util.List;

public class WeatherMapper {

    public WeatherModel getDBModelFromResponse(WeatherModel weatherModel, UserPreferences userPreferences){

        /**
         * Установка правильного имени города из настроеек.
         */
        weatherModel.setCityName(userPreferences.getCityName());
        weatherModel.getLocation().setName(userPreferences.getCityName());

        /**
         * Сетинг дня недели в каждый день.
         */
        for (ForecastDay f: weatherModel.getForecast().getForecastDayList()) {
            f.setDayOfWeek(UtilDate.getDayOfWeek(f.getDateEpoch()));
        }

        return weatherModel;
    }


    public ForecastDTOOutput getDTOFromPOJO(WeatherModel weatherModel, UserPreferences userPreferences) {

        ForecastDTOOutput dtoOutput = new ForecastDTOOutput();

        /**
         * Формирование количетво дней в соответсвии с запросом.
         */
        weatherModel.getForecast().setForecastDayList(weatherModel.getForecast().getForecastDayList().subList(0, userPreferences.getCountDays()));

        /**
         * Сетинг текущих настроек
         */
        ForecastDTOOutput.SettingPref settingPref = new ForecastDTOOutput.SettingPref();
        settingPref.setCelsius(userPreferences.isCelsius());
        settingPref.setMm(userPreferences.isMm());
        settingPref.setKilometers(userPreferences.isKm());
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

        if (userPreferences.isCelsius()) {
            current.setTemp(weatherModel.getCurrent().getTempC());
            current.setFeelslike(weatherModel.getCurrent().getFeelslikeC());
        } else {
            current.setTemp(weatherModel.getCurrent().getTempF());
            current.setFeelslike(weatherModel.getCurrent().getFeelslikeF());
        }
        if (userPreferences.isKm()) {
            current.setWind(weatherModel.getCurrent().getWindKph());
        } else {
            current.setWind(weatherModel.getCurrent().getWindMph());
        }
        if (userPreferences.isMm()){
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
            if (userPreferences.isCelsius()) {
                day.setMinTemp(fDay.getDay().getMintempC());
                day.setMaxTemp(fDay.getDay().getMaxtempC());
            } else {
                day.setMinTemp(fDay.getDay().getMintempF());
                day.setMaxTemp(fDay.getDay().getMaxtempF());
            }

            if (userPreferences.isKm()) {
                day.setWind(fDay.getDay().getMaxwindKph());
            } else {
                day.setWind(fDay.getDay().getMaxwindMph());
            }

            if (userPreferences.isMm()) {
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
