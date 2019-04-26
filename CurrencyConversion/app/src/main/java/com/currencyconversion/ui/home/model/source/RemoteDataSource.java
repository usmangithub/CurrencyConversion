package com.currencyconversion.ui.home.model.source;

import com.currencyconversion.api.ApiManager;
import com.currencyconversion.ui.home.model.CurrencyData;
import com.currencyconversion.ui.home.model.CurrencyName;

import io.reactivex.Single;

public class RemoteDataSource {

    private CurrencyApi currencyApi;

    public RemoteDataSource(ApiManager apiManager) {
        currencyApi = apiManager.getRetrofit().create(CurrencyApi.class);
    }

    public Single<CurrencyName> getCurrencyName() {
        return currencyApi.getCurrencyName();
    }

    public Single<CurrencyData> getCurrencyData(String type) {
        return currencyApi.getCurrencyData(type);
    }

}
