package com.anhhn.weatherdemoapp.pojo.remote;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Andy Ha on 5/8/18.
 */
public class WeatherResponse {
    @SerializedName("sys")
    public Sys sys;
    @SerializedName("weather")
    public List<Weather> weathers;
    @SerializedName("main")
    public Main main;
    @SerializedName("wind")
    public Wind wind;
    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;

}
