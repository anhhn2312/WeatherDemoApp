package com.anhhn.weatherdemoapp.base.fragment.vipe;


import com.anhhn.weatherdemoapp.pojo.response.ErrorHandler;

public abstract class BaseFragmentInteractor<P extends FragmentContract.Presenter>
        implements FragmentContract.Interactor<P> {
  private P mPresenter;

  public BaseFragmentInteractor(P presenter) {
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