package com.currencyconversion.ui.home.model;

import io.reactivex.Single;

public interface CurrencyRepository {
    Single<CurrencyData> getCurrencyDataList(String keyword);

    Single<CurrencyName> getCurrencyNameList();
}
