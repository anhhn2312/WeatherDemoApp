package com.anhhn.weatherdemoapp.ui.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.anhhn.weatherdemoapp.R;
import com.anhhn.weatherdemoapp.base.activity.BaseActivity;
import com.anhhn.weatherdemoapp.pojo.dto.WeatherDTO;
import com.anhhn.weatherdemoapp.utils.AppUtils;
import com.anhhn.weatherdemoapp.utils.Constants;
import com.anhhn.weatherdemoapp.utils.ImageUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Andy Ha on 5/8/18.
 */
public class DetailActivity extends BaseActivity<DetailContract.Presenter> implements DetailContract.View {
    @BindView(R.id.tvCurrentTemp)
    TextView tvCurrentTemp;
    @BindView(R.id.ivWeather)
    SimpleDraweeView ivWeather;
    @BindView(R.id.tvCity)
    TextView tvCity;
    @BindView(R.id.tvMaxTemp)
    TextView tvMaxTemp;
    @BindView(R.id.tvMinTemp)
    TextView tvMinTemp;
    @BindView(R.id.tvHumid)
    TextView tvHumid;
    @BindView(R.id.tvPressure)
    TextView tvPressure;
    @BindView(R.id.tvWindSpeed)
    TextView tvWindSpeed;
    @BindView(R.id.tvSunrise)
    TextView tvSunrise;
    @BindView(R.id.tvSunset)
    TextView tvSunset;

    private WeatherDTO mWeather;
    private int mWeatherId;
    private SimpleDateFormat sdfHour;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sdfHour = new SimpleDateFormat("HH:mm", Locale.getDefault());
        mWeatherId = getIntent().getIntExtra(Constants.Bundle.KEY_WEATHER_ID, 0);
        getPresenter().fetchWeather(String.valueOf(mWeatherId));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_detail;
    }

    @Override
    public void onFetchWeatherSuccess(WeatherDTO weatherDTO) {
        if (mWeather != null)
            showMessage(R.string.weather_updated);
        this.mWeather = weatherDTO;
        bindView();
    }

    @Override
    public DetailContract.Presenter initPresenter() {
        return new DetailPresenter();
    }

    private void bindView() {
        tvCurrentTemp.setText(String.format(getString(R.string.degree_text), mWeather.getCurrentTemp()));
        ImageUtils.loadImage(ivWeather, AppUtils.getInstance().
                getWeatherIconByName(mWeather.getIcon()));
        tvCity.setText(mWeather.getName());
        tvMaxTemp.setText(String.format(getString(R.string.max_temp), mWeather.getMaxTemp()));
        tvMinTemp.setText(String.format(getString(R.string.min_temp), mWeather.getMinTemp()));
        tvHumid.setText(String.format(getString(R.string.humid), mWeather.getHumid()));
        tvPressure.setText(String.format(getString(R.string.pressure), mWeather.getPressure()));
        tvWindSpeed.setText(String.format(getString(R.string.wind_speed), mWeather.getWindSpeed()));
        tvSunrise.setText(String.format(getString(R.string.sunrise),
                sdfHour.format(new Date(mWeather.getSunrise() * 1000))));
        tvSunset.setText(String.format(getString(R.string.sunset),
                sdfHour.format(new Date(mWeather.getSunset() * 1000))));
    }

    @OnClick(R.id.btnRefresh)
    public void onClickRefresh() {
        getPresenter().fetchWeather(String.valueOf(mWeatherId));
    }
}
