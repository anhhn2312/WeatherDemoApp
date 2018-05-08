package com.anhhn.weatherdemoapp.ui.home;

import com.anhhn.weatherdemoapp.base.activity.vipe.BaseActivityInteractor;

/**
 * Created by Andy Ha on 5/8/18.
 */
public class HomeInteractor extends BaseActivityInteractor<HomeContract.Presenter> implements HomeContract.Interactor {

    public HomeInteractor(HomeContract.Presenter presenter) {
        super(presenter);
    }
}
