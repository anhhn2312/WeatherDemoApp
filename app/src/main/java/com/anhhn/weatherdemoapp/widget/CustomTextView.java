package com.anhhn.weatherdemoapp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.anhhn.weatherdemoapp.R;
import com.anhhn.weatherdemoapp.utils.FontManager;


public class CustomTextView extends android.support.v7.widget.AppCompatTextView {
  private String mFontPath;

  public CustomTextView(Context context) {
    this(context, null);
  }

  public CustomTextView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, -1);
  }

  public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }

  public void init(Context context, AttributeSet attrs) {
    if (attrs != null) {
      TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomText);

      mFontPath = typedArray.getString(R.styleable.CustomText_font_path);

      typedArray.recycle();
    }

    if (TextUtils.isEmpty(mFontPath))
      mFontPath = "fonts/Roboto-Light.ttf";

    setTypeface(FontManager.getInstance().getFont(getContext(), mFontPath));
    setIncludeFontPadding(false);
  }

  public void setFont(String fontPath) {
    mFontPath = fontPath;
    setTypeface(FontManager.getInstance().getFont(getContext(), mFontPath));
  }
}
