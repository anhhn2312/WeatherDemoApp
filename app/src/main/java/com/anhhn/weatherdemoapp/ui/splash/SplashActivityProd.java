package com.anhhn.weatherdemoapp.ui.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.anhhn.weatherdemoapp.R;
import com.anhhn.weatherdemoapp.ui.home.HomeActivity;

/**
 * Created by Andy Ha on 5/8/18.
 */
public class SplashActivityProd extends Activity{
    private Handler mHandler;
    private final int HOME_DELAY = 2000;

    Runnable mGotoHomeTask = () -> {
        Intent intent = new Intent(SplashActivityProd.this, HomeActivity.class);
        startActivity(intent);
        finish();
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_prod);
        mHandler = new Handler();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mHandler.postDelayed(mGotoHomeTask, HOME_DELAY);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mHandler.removeCallbacks(mGotoHomeTask);
    }
}
