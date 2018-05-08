package com.anhhn.weatherdemoapp.ui.home;

import com.anhhn.weatherdemoapp.base.activity.vipe.ActivityContract;

import java.util.ArrayList;

/**
 * Created by Andy Ha on 5/8/18.
 */

public class HomeContract {
    interface Interactor extends ActivityContract.Interactor<Presenter> {

    }

    interface View extends ActivityContract.View<Presenter> {

    }

    interface Presenter extends ActivityContract.Presenter<View, Interactor> {

    }
}
