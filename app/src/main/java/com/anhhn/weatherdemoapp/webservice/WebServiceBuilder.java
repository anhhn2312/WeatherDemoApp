package com.anhhn.weatherdemoapp.webservice;

import com.anhhn.weatherdemoapp.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Andy Ha on 5/8/18.
 */

public class WebServiceBuilder {

  private static final int CONNECTION_TIME_OUT = 60000;
  private static final int READ_TIME_OUT = 60000;

  private static WebServiceBuilder mInstance;

  private WeatherService mWeatherService;

  public static WebServiceBuilder getInstance() {
    synchronized (WebServiceBuilder.class) {
      if (mInstance == null) mInstance = new WebServiceBuilder();
    }
    return mInstance;
  }

  private String getBaseUrl() {
    return BuildConfig.BASE_URL;
  }

  public void initServices() {
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    if (BuildConfig.DEBUG)
      interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

    Dispatcher dispatcher = new Dispatcher();
    dispatcher.setMaxRequests(1);

    OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIME_OUT, TimeUnit.MILLISECONDS)
            .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
            .addInterceptor(interceptor)
            .build();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    mWeatherService = retrofit.create(WeatherService.class);
  }

  public WeatherService getWeatherService() {
    return mWeatherService;
  }
}
