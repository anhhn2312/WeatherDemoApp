package com.anhhn.weatherdemoapp.base.fragment.vipe;

import android.content.Context;
import android.support.v4.app.Fragment;


public abstract class BaseFragmentPresenter<V extends FragmentContract.View, I extends FragmentContract.Interactor>
        implements FragmentContract.Presenter<V, I> {
  private V mView;
  private I mInteractor;

  public BaseFragmentPresenter() {
    mView = initView();
    mInteractor = initInteractor();
    mView.setPresenter(this);
  }

  @Override
  public V getView() {
    return mView;
  }

  @Override
  public I getInteractor() {
    return mInteractor;
  }

  @Override
  public Fragment getFragment() {
    return (Fragment) mView;
  }

  @Override
  public Context getContext() {
    return mView.getMvpContext();
  }

  @Override
  public void onError(String error) {
    getView().showMessage(error);
    getView().dismissLoadingDialog();
  }

}
