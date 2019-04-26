package com.currencyconversion.ui.home;

import com.currencyconversion.BaseNavigator;
import com.currencyconversion.BaseView;
import com.currencyconversion.ui.home.model.CurrencyData;
import com.currencyconversion.ui.home.model.CurrencyName;

import io.reactivex.Completable;

public interface HomeContract {

    interface View extends BaseView {
        void showConversionData(CurrencyData currencyData);
        void showCurrencyNameList(CurrencyName list);
        Completable showLoadingAnimation();
        Completable hideLoadingAnimation();
    }

    interface Presenter {
        void doSearch(String keyword);
    }

    interface Navigator extends BaseNavigator {
    }
}
