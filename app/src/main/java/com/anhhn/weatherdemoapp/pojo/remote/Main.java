package com.anhhn.weatherdemoapp.pojo.remote;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Andy Ha on 5/8/18.
 */
public class Main {
    @SerializedName("temp")
    public double temperature;
    @SerializedName("pressure")
    public double pressure;
    @SerializedName("humidity")
    public double humid;
    @SerializedName("temp_min")
    public double minTemp;
    @SerializedName("temp_max")
    public double maxTemp;
}
