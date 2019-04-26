package com.currencyconversion.api;

import android.content.Context;
import android.util.Log;

import com.currencyconversion.R;
import com.currencyconversion.utils.JsonUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.Date;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManagerImpl implements ApiManager {
    private Retrofit retrofit;

    public ApiManagerImpl(Context context) {

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.addInterceptor(chain ->
                ApiManager.interceptNetworkError(context, chain));
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(message ->
                Log.e("ERROR","OkHttp"));
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(logging);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new JsonUtils.UnixDateAdapter()).create();
        retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.api_server_url))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(clientBuilder.build())
                .build();
    }

    @Override
    public Retrofit getRetrofit() {
        return retrofit;
    }

}
