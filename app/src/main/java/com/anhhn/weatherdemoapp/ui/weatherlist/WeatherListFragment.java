package com.anhhn.weatherdemoapp.ui.weatherlist;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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

    public static final int RC_LOCATION = 8888;

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
        if (!mWeathers.isEmpty())
            showMessage(R.string.weather_updated);
        mWeathers.clear();
        mWeathers.addAll(weatherDTOS);
        weatherAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (RC_LOCATION == requestCode && permissions.length == 1
                && grantResults.length == 1) {
            getPresenter().fetchMyLocationWeather();
        }
    }

    private void requestPermissions() {
        if (checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            getPresenter().fetchMyLocationWeather();
        } else {
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, RC_LOCATION);
        }
    }

    private boolean checkPermission(String permission) {
        return ContextCompat.checkSelfPermission(self, permission)
                == PackageManager.PERMISSION_GRANTED;
    }
}
