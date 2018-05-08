package com.anhhn.weatherdemoapp.ui.detail;

import com.anhhn.weatherdemoapp.base.activity.vipe.ActivityContract;
import com.anhhn.weatherdemoapp.pojo.dto.WeatherDTO;

/**
 * Created by Andy Ha on 5/8/18.
 */

public class DetailContract {
    interface Interactor extends ActivityContract.Interactor<Presenter> {
        void fetchWeatherDetail(String id);
    }

    interface View extends ActivityContract.View<Presenter> {
        void onFetchWeatherSuccess(WeatherDTO weatherDTO);
    }

    interface Presenter extends ActivityContract.Presenter<View, Interactor> {
        void fetchWeather(String id);
        void onFetchWeatherSuccess(WeatherDTO weatherDTO);
    }
}
