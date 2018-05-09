package com.anhhn.weatherdemoapp.pojo.response;

import android.content.Context;
import android.system.ErrnoException;

import com.anhhn.weatherdemoapp.R;
import com.anhhn.weatherdemoapp.utils.JsonHelper;
import com.anhhn.weatherdemoapp.utils.NetworkUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.HttpException;

public class ErrorHandler {
  private static final String DEFAULT_MSG = "default_error";
  private static final String ERROR_TAG = "message";
  public static final String OFFLINE_MSG = "offline";

  private Context context;

  private static ErrorHandler sInstance;

  private String mCode;
  private String mMessage;

  public ErrorHandler(){

  }

  public static ErrorHandler getInstance() {
    if (sInstance == null) {
          sInstance = new ErrorHandler();
    }
    return sInstance;
  }

  public void init(Context context) {
    this.context = context;
  }

  public String getCode() {
    return mCode;
  }

  public String getMessage() {
    return mMessage;
  }

  public void handleError(Throwable throwable) {
    if (NetworkUtil.checkNetwork(context) == NetworkUtil.NetworkType.DISCONNECT) {
      mMessage = context.getString(R.string.offline);
    } else {
      if (throwable instanceof HttpException) {
        HttpException httpException = (HttpException) throwable;
        try {
          String errorResponse = httpException.response().errorBody().string();
          if (!JsonHelper.isJson(errorResponse)) {
            mMessage = DEFAULT_MSG;
          } else {
            JsonObject errorObject = new JsonParser().parse(errorResponse).getAsJsonObject();
              ErrorResponse result = JsonHelper.getGson()
                      .fromJson(errorObject, ErrorResponse.class);
              mCode = result.code;
              mMessage = result.message;
          }
        } catch (IOException e) {
          e.printStackTrace();
          mMessage = DEFAULT_MSG;
        }
      }else if(throwable instanceof SocketTimeoutException ||
              throwable instanceof ErrnoException ||
              throwable instanceof ConnectException){
        mMessage = OFFLINE_MSG;
      }
      else {
        mMessage = DEFAULT_MSG;
      }
    }
  }
}
