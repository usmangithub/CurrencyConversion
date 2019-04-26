package com.currencyconversion.dagger.application.module;

import android.content.Context;

import com.currencyconversion.api.ApiManager;
import com.currencyconversion.dagger.application.ForApplication;
import com.currencyconversion.shared_preference.SharedPreferenceManager;
import com.currencyconversion.ui.home.model.CurrencyRepository;
import com.currencyconversion.ui.home.model.CurrencyRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class DataModule {
    @Provides
    @Singleton
    CurrencyRepository provideCurrencyRepository(final @ForApplication Context context,
                                                 SharedPreferenceManager sharedPreferenceManager,
                                                 ApiManager apiManager) {
        return new CurrencyRepositoryImpl(sharedPreferenceManager, apiManager);
    }
}
