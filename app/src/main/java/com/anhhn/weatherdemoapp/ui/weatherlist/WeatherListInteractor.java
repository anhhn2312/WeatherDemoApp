package com.anhhn.weatherdemoapp.ui.weatherlist;

import android.annotation.SuppressLint;

import com.anhhn.weatherdemoapp.base.fragment.vipe.BaseFragmentInteractor;
import com.anhhn.weatherdemoapp.pojo.dto.WeatherDTO;
import com.anhhn.weatherdemoapp.pojo.remote.WeatherResponse;
import com.anhhn.weatherdemoapp.utils.Constants;
import com.anhhn.weatherdemoapp.webservice.WebServiceBuilder;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Andy Ha on 5/8/18.
 */
public class WeatherListInteractor extends BaseFragmentInteractor<WeatherListContract.Presenter> implements WeatherListContract.Interactor {

    public WeatherListInteractor(WeatherListContract.Presenter presenter) {
        super(presenter);
    }

    @SuppressLint("CheckResult")
    @Override
    public void fetchWeather(String ids) {
        WebServiceBuilder.getInstance().getWeatherService().getListCityWeather(ids, Constants.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listBaseResponse -> {
                    List<WeatherDTO> weathers = new ArrayList<>();
                    for(WeatherResponse weatherResponse: listBaseResponse.data)
                        weathers.add(WeatherDTO.convert(weatherResponse));
                    getPresenter().onFetchWeatherSuccess(weathers);
                }, this::handleError);
    }
}
