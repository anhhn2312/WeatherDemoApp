package com.anhhn.weatherdemoapp.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.anhhn.weatherdemoapp.R;
import com.anhhn.weatherdemoapp.base.activity.BaseActivity;
import com.anhhn.weatherdemoapp.ui.weatherlist.WeatherListPresenter;

/**
 * Created by Andy Ha on 5/8/18.
 */
public class HomeActivity extends BaseActivity<HomeContract.Presenter> implements HomeContract.View {

    private WeatherListPresenter weatherListPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        weatherListPresenter = new WeatherListPresenter();
        addFragment(R.id.listContainer, weatherListPresenter.getFragment());

        Fragment buttonFragment = new ButtonFragment();
        addFragment(R.id.buttonContainer, buttonFragment);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_home;
    }

    @Override
    public HomeContract.Presenter initPresenter() {
        return new HomePresenter();
    }

    private void addFragment(int container, Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .add(container, fragment)
                .commit();
    }

    public void refreshList() {
        weatherListPresenter.fetchWeather();
    }
}
