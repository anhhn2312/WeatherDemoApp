package com.anhhn.weatherdemoapp.pojo.remote;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Andy Ha on 5/8/18.
 */
public class Wind {
    @SerializedName("speed")
    public double speed;
    @SerializedName("deg")
    public double degree;
}
