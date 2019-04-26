package com.currencyconversion.ui.home.model.source;

import android.content.SharedPreferences;

import com.currencyconversion.shared_preference.SharedPreferenceManager;
import com.currencyconversion.ui.home.model.CurrencyData;
import com.currencyconversion.ui.home.model.CurrencyName;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

public class LocalDataSource {
    private static final String CURRENCY_SHARED_PREFERENCE = "currency_shared_preference";

    private SharedPreferences sharedPreferences;

    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public LocalDataSource(SharedPreferenceManager sharedPreferenceManager) {
        this.sharedPreferences = sharedPreferenceManager
                .getSharedPreferences(CURRENCY_SHARED_PREFERENCE);
    }

    public void saveCurrencyData(CurrencyData currencyData, String keyword) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("currency_data_" + keyword, new Gson().toJson(currencyData));
        editor.putLong("currency_data_timestamp_" + keyword, System.currentTimeMillis());
        editor.apply();
    }

    public void saveCurrencyName(CurrencyName currencyName) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("currency_name", new Gson().toJson(currencyName));
        editor.putString("currency_name_timestamp", dateFormat.format(new Date()));
        editor.apply();
    }

    public CurrencyName getCurrencyName() {
        String obj = sharedPreferences.getString("currency_name", "");
        if (obj.equals("")) {
            return null;
        } else {
            return new Gson().fromJson(obj, new TypeToken<CurrencyName>() {
            }.getType());
        }
    }

    public CurrencyData getCurrencyData(String keyword) {
        String obj = sharedPreferences.getString("currency_data_" + keyword, "");
        if (obj.equals("")) {
            return null;
        } else {
            return new Gson().fromJson(obj, new TypeToken<CurrencyData>() {
            }.getType());
        }
    }

    public boolean hasCurrencyData(String keyword) {
        long timeDiff = System.currentTimeMillis() -
                sharedPreferences.getLong("currency_data_timestamp_" + keyword, 0);
        return timeDiff < 30 * 60 * 1000;
    }

    public boolean hasCurrencyName() {
        String date = sharedPreferences.getString("currency_name_timestamp", "");
        return date.equals(dateFormat.format(new Date()));
    }
}
