package com.anhhn.weatherdemoapp.ui.detail;

import com.anhhn.weatherdemoapp.base.activity.vipe.BaseActivityPresenter;
import com.anhhn.weatherdemoapp.pojo.dto.WeatherDTO;

/**
 * Created by Andy Ha on 5/8/18.
 */

public class DetailPresenter extends BaseActivityPresenter<DetailContract.View, DetailContract.Interactor>
        implements DetailContract.Presenter {

    @Override
    public void fetchWeather(String id) {
        getInteractor().fetchWeatherDetail(id);
        getView().showLoadingDialog();
    }

    @Override
    public DetailContract.Interactor initInteractor() {
        return new DetailInteractor(this);
    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void onFetchWeatherSuccess(WeatherDTO weatherDTO) {
        getView().onFetchWeatherSuccess(weatherDTO);
        getView().dismissLoadingDialog();
    }
}

