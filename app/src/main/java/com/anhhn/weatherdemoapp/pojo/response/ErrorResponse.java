package com.anhhn.weatherdemoapp.pojo.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Andy Ha on 5/8/18.
 */
public class ErrorResponse {
    @SerializedName("cod")
    public String code;
    @SerializedName("message")
    public String message;
}
