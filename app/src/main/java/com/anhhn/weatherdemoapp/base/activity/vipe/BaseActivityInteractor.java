package com.anhhn.weatherdemoapp.base.activity.vipe;

import com.anhhn.weatherdemoapp.pojo.response.ErrorHandler;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;


public abstract class BaseActivityInteractor<P extends ActivityContract.Presenter>
        implements ActivityContract.Interactor<P> {
  private P mPresenter;

  public BaseActivityInteractor(P presenter) {
    mPresenter = presenter;
  }

  @Override
  public P getPresenter() {
    return mPresenter;
  }

  public void handleError(Throwable throwable){
    ErrorHandler errorHandler = ErrorHandler.getInstance();
    errorHandler.handleError(throwable);
    if(errorHandler.getMessage() != null)
      getPresenter().onError(errorHandler.getMessage());
  }


}
