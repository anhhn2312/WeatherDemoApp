package com.anhhn.weatherdemoapp.utils;

import android.content.Context;

/**
 * Created by Andy Ha on 5/8/18.
 */

public class AppUtils {

  private static AppUtils mInstance = null;
  private Context mContext;

  public static synchronized  AppUtils getInstance() {
    if(mInstance ==null) mInstance = new AppUtils();
    return mInstance;
  }

  public void init(Context context) {
    this.mContext = context;
  }

  public int getWeatherIconByName(String identifier){
    return getDrawableResourceByName("icon".concat(identifier));
  }

  public int getDrawableResourceByName(String identifier){
    try {
      String packageName = mContext.getPackageName();
      return mContext.getResources().getIdentifier(identifier, "drawable", packageName);
    }catch(Exception e){
      return 0;
    }
  }

}
