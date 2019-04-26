package com.currencyconversion.ui.home.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class CurrencyData {
    @SerializedName("quotes")
    private Map<String, String> currencyDataMap;

    public CurrencyData(Map<String, String> currencyDataMap) {
        this.currencyDataMap = currencyDataMap;
    }

    public Map<String, String> getCurrencyData() {
        return currencyDataMap;
    }
}
