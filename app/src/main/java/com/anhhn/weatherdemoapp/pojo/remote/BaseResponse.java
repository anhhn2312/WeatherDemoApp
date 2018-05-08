package com.anhhn.weatherdemoapp.pojo.remote;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Andy Ha on 5/8/18.
 */
public class BaseResponse<T> {
    @SerializedName("cnt")
    public int count;
    @SerializedName("list")
    public T data;
    @SerializedName("cod")
    public int code;
    @SerializedName("message")
    public String message;
}
