package com.anhhn.weatherdemoapp.base;

import android.app.Application;

import com.anhhn.weatherdemoapp.pojo.response.ErrorHandler;
import com.anhhn.weatherdemoapp.utils.AppUtils;
import com.anhhn.weatherdemoapp.webservice.WebServiceBuilder;
import com.crashlytics.android.Crashlytics;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.leakcanary.LeakCanary;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Andy Ha on 5/8/18.
 */
public class WeatherDemoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
        WebServiceBuilder.getInstance().initServices();
        AppUtils.getInstance().init(this);
        Fresco.initialize(this);
        ErrorHandler.getInstance().init(this);
        Fabric.with(this, new Crashlytics());
    }
}
