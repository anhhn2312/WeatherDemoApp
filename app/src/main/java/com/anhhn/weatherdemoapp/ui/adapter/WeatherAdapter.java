package com.anhhn.weatherdemoapp.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anhhn.weatherdemoapp.R;
import com.anhhn.weatherdemoapp.pojo.dto.WeatherDTO;
import com.anhhn.weatherdemoapp.ui.detail.DetailActivity;
import com.anhhn.weatherdemoapp.utils.AppUtils;
import com.anhhn.weatherdemoapp.utils.Constants;
import com.anhhn.weatherdemoapp.utils.ImageUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Andy Ha on 5/3/18.
 */
public class WeatherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<WeatherDTO> mWeathers;
    private LayoutInflater mInflater;
    private Activity mContext;

    public WeatherAdapter(Activity mActivity, List<WeatherDTO> channels) {
        mInflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mContext = mActivity;
        this.mWeathers = channels;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_weather, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof Holder) {
            Holder itemHolder = (Holder) holder;

            WeatherDTO weather = mWeathers.get(position);
            itemHolder.tvCity.setText(weather.getName());
            itemHolder.tvTemp.setText(String.format(mContext.getString(R.string.degree_text),
                    weather.getCurrentTemp()));
            itemHolder.tvWeather.setText(weather.getWeatherDescription());
            ImageUtils.loadImage(itemHolder.ivIcon, AppUtils.getInstance().
                    getWeatherIconByName(weather.getIcon()));
        }
    }

    @Override
    public int getItemCount() {
        return mWeathers.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        SimpleDraweeView ivIcon;
        TextView tvCity, tvTemp, tvWeather;

        public Holder(View v) {
            super(v);
            ivIcon = v.findViewById(R.id.ivIcon);
            tvCity = v.findViewById(R.id.tvCity);
            tvWeather = v.findViewById(R.id.tvWeather);
            tvTemp = v.findViewById(R.id.tvTemp);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtra(Constants.Bundle.KEY_WEATHER_ID, mWeathers.get(getAdapterPosition()).getId());
            mContext.startActivity(intent);
        }
    }
}
