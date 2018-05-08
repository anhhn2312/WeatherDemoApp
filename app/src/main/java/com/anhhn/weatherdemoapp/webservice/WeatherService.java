package com.anhhn.weatherdemoapp.webservice;

import com.anhhn.weatherdemoapp.pojo.remote.BaseResponse;
import com.anhhn.weatherdemoapp.pojo.remote.WeatherResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Andy Ha on 5/8/18.
 */
public interface WeatherService {
  @GET("data/2.5/group?units=metric")
  Single<BaseResponse<List<WeatherResponse>>> getListCityWeather(@Query("id") String cityId,
                                                                 @Query("appid") String apiKey);
  @GET("data/2.5/weather?units=metric")
  Single<WeatherResponse> getWeatherDetail(@Query("id") String cityId,
                                                         @Query("appid") String apiKey);

  @GET("data/2.5/weather?units=metric")
  Single<WeatherResponse> getWeatherByCoordinate(@Query("appid") String apiKey,
                                                 @Query("lat") double latitude,
                                                 @Query("lon") double longitude);

}
