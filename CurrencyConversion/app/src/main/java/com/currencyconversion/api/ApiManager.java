package com.currencyconversion.api;

import android.content.Context;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import okhttp3.Interceptor;
import okhttp3.Response;
import retrofit2.Retrofit;

public interface ApiManager {

    Retrofit getRetrofit();

    static Response interceptNetworkError(Context context, Interceptor.Chain chain) throws IOException {
        try {
            return chain.proceed(chain.request());
        }
        catch (SocketTimeoutException | UnknownHostException exception) {
            return chain.proceed(chain.request());
        }
    }
}
