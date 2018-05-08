package com.anhhn.weatherdemoapp.ui.home;

import com.anhhn.weatherdemoapp.base.activity.vipe.BaseActivityPresenter;

/**
 * Created by Andy Ha on 5/8/18.
 */

public class HomePresenter extends BaseActivityPresenter<HomeContract.View, HomeContract.Interactor>
        implements HomeContract.Presenter {

    @Override
    public HomeContract.Interactor initInteractor() {
        return new HomeInteractor(this);
    }

    @Override
    public void onError(String error) {

    }
}

