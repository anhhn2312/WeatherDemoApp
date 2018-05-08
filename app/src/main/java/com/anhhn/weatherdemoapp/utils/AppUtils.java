package com.anhhn.weatherdemoapp.utils;

import android.content.Context;

/**
 * Created by Andy Ha on 5/8/18.
 */

public class AppUtils {

  private static AppUtils mInstance = null;
  private Context mContext;

  public synchronized  static AppUtils getInstance() {
    if(mInstance ==null) mInstance = new AppUtils();
    return mInstance;
  }

  public void init(Context context) {
    this.mContext = context;
  }

  public int getDrawableResourceByName(String aString){
    try {
      String packageName = mContext.getPackageName();
      int resId = mContext.getResources().getIdentifier(aString, "drawable", packageName);
      return resId;
    }catch(Exception e){
      return 0;
    }
  }

}
