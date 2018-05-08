package com.anhhn.weatherdemoapp.base;

import android.app.Application;

import com.anhhn.weatherdemoapp.pojo.response.ErrorHandler;
import com.anhhn.weatherdemoapp.utils.AppUtils;
import com.anhhn.weatherdemoapp.webservice.WebServiceBuilder;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Andy Ha on 5/8/18.
 */
public class WeatherDemoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        WebServiceBuilder.getInstance().initServices();
        AppUtils.getInstance().init(this);
        Fresco.initialize(this);
        ErrorHandler.getInstance().init(this);
    }
}
