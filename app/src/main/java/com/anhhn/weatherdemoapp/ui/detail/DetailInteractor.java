package com.anhhn.weatherdemoapp.ui.detail;

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
public class DetailInteractor extends BaseActivityInteractor<DetailContract.Presenter> implements DetailContract.Interactor {

    public DetailInteractor(DetailContract.Presenter presenter) {
        super(presenter);
    }

    @SuppressLint("CheckResult")
    @Override
    public void fetchWeatherDetail(String id) {
        WebServiceBuilder.getInstance().getWeatherService().getWeatherDetail(id, Constants.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weatherResponse -> {
                    WeatherDTO weatherDTO = WeatherDTO.convert(weatherResponse);
                    getPresenter().onFetchWeatherSuccess(weatherDTO);
                }, this::handleError);
    }
}
