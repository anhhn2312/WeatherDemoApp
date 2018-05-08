package com.anhhn.weatherdemoapp.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import com.anhhn.weatherdemoapp.R;

public class DialogUtils {
  private static Dialog sDialog;

  public static Dialog createLoadingDialog(Context context, DialogInterface.OnCancelListener onCancelListener) {
    Dialog dialog = new Dialog(context);
    dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    dialog.setContentView(R.layout.dialog_loading);
    dialog.setCancelable(false);
    dialog.setOnCancelListener(onCancelListener);
    return dialog;
  }
}
