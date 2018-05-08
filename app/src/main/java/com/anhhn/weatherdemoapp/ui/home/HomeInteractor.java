package com.anhhn.weatherdemoapp.ui.home;

import android.annotation.SuppressLint;

import com.anhhn.weatherdemoapp.base.activity.vipe.BaseActivityInteractor;
import com.anhhn.weatherdemoapp.pojo.dto.WeatherDTO;
import com.anhhn.weatherdemoapp.utils.Constants;
import com.anhhn.weatherdemoapp.webservice.WebServiceBuilder;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Andy Ha on 5/8/18.
 */
@SuppressLint("CheckResult")
public class HomeInteractor extends BaseActivityInteractor<HomeContract.Presenter> implements HomeContract.Interactor {

    public HomeInteractor(HomeContract.Presenter presenter) {
        super(presenter);
    }

    @Override
    public void fetchMyLocationWeather(double lat, double lon) {
        WebServiceBuilder.getInstance().getWeatherService().getWeatherByCoordinate(Constants.API_KEY, lat, lon)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weatherResponse -> {
                    WeatherDTO weatherDTO = WeatherDTO.convert(weatherResponse);
                    getPresenter().onFetchMyLocationWeatherSuccess(weatherDTO);
                }, this::handleError);
    }
}
