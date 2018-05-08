package com.anhhn.weatherdemoapp.ui.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.anhhn.weatherdemoapp.BuildConfig;
import com.anhhn.weatherdemoapp.utils.Constants;

/**
 * Created by Andy Ha on 5/8/18.
 */
public class StartActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Decide if Splash screen for production or dev environment
        // should be presented
        Intent intent = new Intent(this,
                BuildConfig.ENVIRONMENT.equals(Constants.ENVIRONMENT.ENV_PROD) ?
                        SplashActivityProd.class : SplashActivityDev.class);
        startActivity(intent);
        finish();
    }
}
