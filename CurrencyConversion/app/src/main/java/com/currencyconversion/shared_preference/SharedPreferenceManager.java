package com.currencyconversion.shared_preference;

import android.content.SharedPreferences;

public interface SharedPreferenceManager {
    SharedPreferences getSharedPreferences();

    SharedPreferences getSharedPreferences(String id);
}
