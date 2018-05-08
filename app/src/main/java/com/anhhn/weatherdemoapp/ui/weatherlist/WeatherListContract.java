package com.anhhn.weatherdemoapp.ui.weatherlist;

import com.anhhn.weatherdemoapp.base.activity.vipe.ActivityContract;
import com.anhhn.weatherdemoapp.base.fragment.vipe.FragmentContract;
import com.anhhn.weatherdemoapp.pojo.dto.WeatherDTO;

import java.util.List;

/**
 * Created by Andy Ha on 5/8/18.
 */

public class WeatherListContract {
    interface Interactor extends FragmentContract.Interactor<Presenter> {
        void fetchWeather(String ids);
    }

    interface View extends FragmentContract.View<Presenter> {
        void onFetchWeatherSuccess(List<WeatherDTO> weatherDTOS);
    }

    interface Presenter extends FragmentContract.Presenter<View, Interactor> {
        void fetchWeather();
        void onFetchWeatherSuccess(List<WeatherDTO> weatherDTOS);

        void fetchMyLocationWeather();
    }
}
