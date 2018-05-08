package com.anhhn.weatherdemoapp.base.activity.vipe;

import android.content.Context;
import android.support.annotation.StringRes;

import io.reactivex.disposables.Disposable;


public interface ActivityContract {
  interface Interactor<P extends Presenter> {
    P getPresenter();
  }

  interface View<P extends Presenter> {
    P initPresenter();

    P getPresenter();

    void showMessage(String message);

    void showMessage(@StringRes int stringResId);

    void showLoadingDialog();

    void dismissLoadingDialog();

    void showErrorToast(String errorCode);
  }

  interface Presenter<V extends View, I extends Interactor> {
    V getView();

    I initInteractor();

    I getInteractor();

    void attachView(V view);

    Context getContext();

    void onError(String error);
  }
}
