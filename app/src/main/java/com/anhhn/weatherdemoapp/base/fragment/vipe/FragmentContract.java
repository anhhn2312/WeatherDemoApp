package com.anhhn.weatherdemoapp.base.fragment.vipe;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;


public interface FragmentContract {
  interface Interactor<P extends Presenter> {
    P getPresenter();
  }

  interface View<P extends Presenter> {
    P getPresenter();

    void setPresenter(P presenter);

    Context getMvpContext();

    void showLoadingDialog();

    void dismissLoadingDialog();

    void showMessage(String message);

    void showMessage(@StringRes int messageResId);
  }

  interface Presenter<V extends View, I extends Interactor> {
    V initView();

    I initInteractor();

    V getView();

    I getInteractor();

    Fragment getFragment();

    Context getContext();

    void onError(String error);
  }
}
