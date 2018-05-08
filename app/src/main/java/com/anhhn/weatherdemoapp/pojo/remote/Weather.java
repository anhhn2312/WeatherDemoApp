package com.anhhn.weatherdemoapp.pojo.remote;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Andy Ha on 5/8/18.
 */
public class Weather {
    @SerializedName("id")
    public int id;
    @SerializedName("main")
    public String main;
    @SerializedName("description")
    public String description;
    @SerializedName("icon")
    public String icon;
}
