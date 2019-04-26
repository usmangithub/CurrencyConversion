package com.currencyconversion.dagger.application.module;

import android.content.Context;
import android.content.res.Resources;

import com.currencyconversion.TestApplication;
import com.currencyconversion.api.ApiManager;
import com.currencyconversion.api.ApiManagerImpl;
import com.currencyconversion.dagger.application.ForApplication;
import com.currencyconversion.shared_preference.SharedPreferenceManager;
import com.currencyconversion.shared_preference.SharedPreferenceManagerImpl;
import com.currencyconversion.ui.home.model.CurrencyRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class ApplicationModule {

    private final TestApplication testApplication;

    public ApplicationModule(final TestApplication testApplication) {
        this.testApplication = testApplication;
    }

    @Provides
    @Singleton
    TestApplication provideTestApplication() {
        return testApplication;
    }

    @Provides
    @Singleton
    @ForApplication
    Context provideContext() {
        return testApplication;
    }

    @Provides
    @Singleton
    Resources provideResources() {
        return testApplication.getResources();
    }

    @Provides
    @Singleton
    SharedPreferenceManager provideSharedPreferenceManager(final @ForApplication Context context) {
        return new SharedPreferenceManagerImpl(context);
    }

    @Provides
    @Singleton
    ApiManager provideApiManager(final @ForApplication Context context) {
        return new ApiManagerImpl(context);
    }

    public interface Exposes {
        TestApplication testApplication();
        @ForApplication
        Context context();
        Resources resources();
        ApiManager spiManager();
        SharedPreferenceManager sharedPreferenceManager();
        CurrencyRepository currencyRepository();
    }
}
