package com.anhhn.weatherdemoapp.base.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.anhhn.weatherdemoapp.base.activity.vipe.ActivityContract;
import com.anhhn.weatherdemoapp.utils.DialogUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity<P extends ActivityContract.Presenter> extends FragmentActivity
        implements ActivityContract.View<P>, DialogInterface.OnCancelListener {
  private P mPresenter;
  private Unbinder mUnbinder;
  private Dialog mLoadingDialog;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutResId());

    mUnbinder = ButterKnife.bind(this);

    mPresenter = initPresenter();
    mPresenter.attachView(this);

    mLoadingDialog = DialogUtils.createLoadingDialog(this, this);
  }

  @Override
  protected void onDestroy() {
    mUnbinder.unbind();
    super.onDestroy();
  }

  @Override
  public P getPresenter() {
    return mPresenter;
  }

  @Override
  public void showMessage(String message) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void showMessage(@StringRes int stringResId) {
    Toast.makeText(this, stringResId, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void showErrorToast(String errorCode) {
    //Todo
  }

  @Override
  public void showLoadingDialog() {
    if (!mLoadingDialog.isShowing())
      mLoadingDialog.show();
  }

  @Override
  public void dismissLoadingDialog() {
    if (mLoadingDialog != null && mLoadingDialog.isShowing())
      mLoadingDialog.dismiss();
  }

  @Override
  public void onCancel(DialogInterface dialogInterface) {
  }

  protected abstract int getLayoutResId();

}
