package com.anhhn.weatherdemoapp.base.activity.vipe;

import android.content.Context;


public abstract class BaseActivityPresenter<V extends ActivityContract.View, I extends ActivityContract.Interactor>
        implements ActivityContract.Presenter<V, I> {
  private V mView;
  private I mInteractor;

  public BaseActivityPresenter() {
    mInteractor = initInteractor();
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
  public void attachView(V view) {
    mView = view;
  }

  @Override
  public Context getContext() {
    return (Context) mView;
  }

  @Override
  public void onError(String error) {
    getView().showMessage(error);
    getView().dismissLoadingDialog();
  }
}
