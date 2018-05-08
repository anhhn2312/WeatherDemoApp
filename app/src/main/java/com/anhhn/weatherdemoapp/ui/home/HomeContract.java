package com.anhhn.weatherdemoapp.ui.home;

import com.anhhn.weatherdemoapp.base.activity.vipe.ActivityContract;
import com.anhhn.weatherdemoapp.pojo.dto.WeatherDTO;

/**
 * Created by Andy Ha on 5/8/18.
 */

public class HomeContract {
    interface Interactor extends ActivityContract.Interactor<Presenter> {
        void fetchMyLocationWeather(double lat, double lon);
    }

    interface View extends ActivityContract.View<Presenter> {
        void onFetchMyLocationWeatherSuccess(WeatherDTO weatherDTO);
    }

    interface Presenter extends ActivityContract.Presenter<View, Interactor> {
        void fetchMyLocationWeather(double lat, double lon);
        void onFetchMyLocationWeatherSuccess(WeatherDTO weatherDTO);
    }
}
