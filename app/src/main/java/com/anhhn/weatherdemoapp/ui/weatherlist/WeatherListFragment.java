package com.anhhn.weatherdemoapp.ui.weatherlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.anhhn.weatherdemoapp.R;
import com.anhhn.weatherdemoapp.base.fragment.BaseFragment;
import com.anhhn.weatherdemoapp.pojo.dto.WeatherDTO;
import com.anhhn.weatherdemoapp.ui.adapter.WeatherAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Andy Ha on 5/8/18.
 */
public class WeatherListFragment extends BaseFragment<WeatherListContract.Presenter> implements WeatherListContract.View {
    @BindView(R.id.rvWeather)
    RecyclerView rvWeather;

    private WeatherAdapter weatherAdapter;
    private List<WeatherDTO> mWeathers;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_weather_list;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvWeather.setLayoutManager(new LinearLayoutManager(self, LinearLayoutManager.VERTICAL, false));
        mWeathers = new ArrayList<>();
        weatherAdapter = new WeatherAdapter(self, mWeathers);
        rvWeather.setAdapter(weatherAdapter);

        getPresenter().fetchWeather();
    }

    @Override
    public void onFetchWeatherSuccess(List<WeatherDTO> weatherDTOS) {
        if(mWeathers.size() > 0)
            showMessage(R.string.weather_updated);
        mWeathers.clear();
        mWeathers.addAll(weatherDTOS);
        weatherAdapter.notifyDataSetChanged();
    }
}
