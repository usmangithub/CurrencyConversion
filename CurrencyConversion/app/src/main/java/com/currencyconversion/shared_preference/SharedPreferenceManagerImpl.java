package com.currencyconversion.shared_preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferenceManagerImpl implements SharedPreferenceManager {

    private Context context;

    public  SharedPreferenceManagerImpl(Context context) {
        this.context = context;
    }

    @Override
    public SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public SharedPreferences getSharedPreferences(String id) {
        return context.getSharedPreferences(id, Context.MODE_PRIVATE);
    }
}
