package com.currencyconversion.ui.home.model.source;

import com.currencyconversion.ui.home.model.CurrencyData;
import com.currencyconversion.ui.home.model.CurrencyName;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CurrencyApi {
    @GET("list?access_key=393d7e91eea017841fc9bf9fe784e94f")
    Single<CurrencyName> getCurrencyName();

    @GET("live?access_key=393d7e91eea017841fc9bf9fe784e94f")
    Single<CurrencyData> getCurrencyData(@Query("source") String source);
}
