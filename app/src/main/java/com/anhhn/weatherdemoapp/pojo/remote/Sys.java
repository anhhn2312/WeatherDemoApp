package com.anhhn.weatherdemoapp.pojo.remote;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Andy Ha on 5/8/18.
 */
public class Sys {
    @SerializedName("sunrise")
    public long sunrise;
    @SerializedName("sunset")
    public long sunset;
}
