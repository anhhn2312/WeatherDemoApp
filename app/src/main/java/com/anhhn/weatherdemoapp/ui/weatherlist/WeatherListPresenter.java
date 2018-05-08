package com.anhhn.weatherdemoapp.ui.weatherlist;

import com.anhhn.weatherdemoapp.base.fragment.vipe.BaseFragmentPresenter;
import com.anhhn.weatherdemoapp.pojo.dto.WeatherDTO;
import com.anhhn.weatherdemoapp.utils.Constants;

import java.util.List;

/**
 * Created by Andy Ha on 5/8/18.
 */

public class WeatherListPresenter extends BaseFragmentPresenter<WeatherListContract.View, WeatherListContract.Interactor>
        implements WeatherListContract.Presenter {

    @Override
    public WeatherListContract.View initView() {
        return new WeatherListFragment();
    }

    @Override
    public WeatherListContract.Interactor initInteractor() {
        return new WeatherListInteractor(this);
    }

    @Override
    public void fetchWeather() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<Constants.LOCATIONS.length; i++){
            sb.append(Constants.LOCATIONS[i]);
            if(i<Constants.LOCATIONS.length-1)
                sb.append(",");
        }
        String ids = sb.toString();
        getInteractor().fetchWeather(ids);
        getView().showLoadingDialog();
    }

    @Override
    public void onError(String error) {
        getView().showMessage(error);
    }

    @Override
    public void onFetchWeatherSuccess(List<WeatherDTO> weatherDTOS) {
        getView().onFetchWeatherSuccess(weatherDTOS);
        getView().dismissLoadingDialog();
    }
}

