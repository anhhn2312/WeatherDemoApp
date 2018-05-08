package com.anhhn.weatherdemoapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {
    public static NetworkType checkNetwork(Context context) {

        if (context == null) return NetworkType.DISCONNECT;

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetInfo = manager.getActiveNetworkInfo();
        if (activeNetInfo == null || !activeNetInfo.isAvailable()) {
            return NetworkType.DISCONNECT;
        } else {
            if (activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                return NetworkType.MOBILE;
            } else {
                return NetworkType.WIFI;
            }
        }
    }

    public enum NetworkType {
        DISCONNECT,
        WIFI,
        MOBILE
    }
}
