package com.anhhn.weatherdemoapp.ui.home;

import com.anhhn.weatherdemoapp.base.activity.vipe.BaseActivityPresenter;
import com.anhhn.weatherdemoapp.pojo.dto.WeatherDTO;

/**
 * Created by Andy Ha on 5/8/18.
 */

public class HomePresenter extends BaseActivityPresenter<HomeContract.View, HomeContract.Interactor>
        implements HomeContract.Presenter {

    @Override
    public HomeContract.Interactor initInteractor() {
        return new HomeInteractor(this);
    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void fetchMyLocationWeather(double lat, double lon) {
        getInteractor().fetchMyLocationWeather(lat, lon);
    }

    @Override
    public void onFetchMyLocationWeatherSuccess(WeatherDTO weatherDTO) {
        getView().onFetchMyLocationWeatherSuccess(weatherDTO);
    }
}

