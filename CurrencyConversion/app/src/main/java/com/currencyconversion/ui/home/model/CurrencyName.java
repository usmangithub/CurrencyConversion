package com.currencyconversion.ui.home.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class CurrencyName {
    @SerializedName("currencies")
    private Map<String, String> currenciesMap;

    public CurrencyName(Map<String, String> currenciesMap) {
        this.currenciesMap = currenciesMap;
    }

    public Map<String, String> getCurrenciesMap() {
        return currenciesMap;
    }
}
