package com.anhhn.weatherdemoapp.base.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.anhhn.weatherdemoapp.base.activity.BaseActivity;
import com.anhhn.weatherdemoapp.base.fragment.vipe.FragmentContract;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends FragmentContract.Presenter> extends Fragment
        implements FragmentContract.View<P> {
  protected Activity self;
  private P mBaseFragmentPresenter;
  private Unbinder mUnbinder;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    self = getActivity();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(getLayoutResId(), container, false);
    mUnbinder = ButterKnife.bind(this, view);
    return view;
  }

  @Override
  public void onDestroyView() {
    mUnbinder.unbind();
    super.onDestroyView();
  }

  @Override
  public P getPresenter() {
    return mBaseFragmentPresenter;
  }

  @Override
  public void setPresenter(P presenter) {
    mBaseFragmentPresenter = presenter;
  }

  @Override
  public Context getMvpContext() {
    return getActivity();
  }

  @Override
  public void showLoadingDialog() {
    getBaseFragmentActivity().showLoadingDialog();
  }

  @Override
  public void dismissLoadingDialog() {
    if (getBaseFragmentActivity() != null)
      getBaseFragmentActivity().dismissLoadingDialog();
  }

  @Override
  public void showMessage(String bfMessage) {
    if (getActivity() != null && bfMessage != null)
      Toast.makeText(getActivity(), bfMessage, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void showMessage(@StringRes int bfMessageResId) {
    if (getActivity() != null)
      Toast.makeText(getActivity(), bfMessageResId, Toast.LENGTH_SHORT).show();
  }

  protected BaseActivity getBaseFragmentActivity() {
    return (BaseActivity) getActivity();
  }

  protected abstract int getLayoutResId();

  protected void onRefuseLogin() {
  }
}
