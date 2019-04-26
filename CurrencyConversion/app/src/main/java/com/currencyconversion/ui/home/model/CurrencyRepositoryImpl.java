package com.currencyconversion.ui.home.model;

import com.currencyconversion.api.ApiManager;
import com.currencyconversion.shared_preference.SharedPreferenceManager;
import com.currencyconversion.ui.home.model.source.LocalDataSource;
import com.currencyconversion.ui.home.model.source.RemoteDataSource;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.SingleSubject;

public class CurrencyRepositoryImpl implements CurrencyRepository {
    private LocalDataSource localDataSource;
    private RemoteDataSource remoteDataSource;

    @Inject
    public CurrencyRepositoryImpl(SharedPreferenceManager sharedPreferenceManager,
                                  ApiManager apiManager) {
        localDataSource = new LocalDataSource(sharedPreferenceManager);
        remoteDataSource = new RemoteDataSource(apiManager);
    }

    @Override
    public Single<CurrencyData> getCurrencyDataList(String keyword) {
        SingleSubject<CurrencyData> singleSubject = SingleSubject.create();
        Schedulers.io().scheduleDirect(() -> {
            if (localDataSource.hasCurrencyData(keyword)) {
                singleSubject.onSuccess(localDataSource.getCurrencyData(keyword));
            } else {
                remoteDataSource.getCurrencyData(keyword)
                        .subscribe(currencyData -> {
                            localDataSource.saveCurrencyData(currencyData, keyword);
                            singleSubject.onSuccess(currencyData);
                        }, Throwable::printStackTrace);
            }
        });
        return singleSubject;
    }

    @Override
    public Single<CurrencyName> getCurrencyNameList() {
        SingleSubject<CurrencyName> singleSubject = SingleSubject.create();
        Schedulers.io().scheduleDirect(() -> {
            if (localDataSource.hasCurrencyName()) {
                singleSubject.onSuccess(localDataSource.getCurrencyName());
            } else {
                remoteDataSource.getCurrencyName()
                        .subscribe(currencyName -> {
                            localDataSource.saveCurrencyName(currencyName);
                            singleSubject.onSuccess(currencyName);
                        }, Throwable::printStackTrace);
            }
        });
        return singleSubject;
    }
}
