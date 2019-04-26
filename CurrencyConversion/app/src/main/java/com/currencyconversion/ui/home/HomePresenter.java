package com.currencyconversion.ui.home;

import com.currencyconversion.BasePresenter;
import com.currencyconversion.ui.home.model.CurrencyRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class HomePresenter
        extends BasePresenter<HomeContract.View, HomeContract.Navigator>
        implements HomeContract.Presenter {

    private Disposable currencyDataDisposable;
    private Disposable currencyNameDisposable;

    @Inject
    CurrencyRepository currencyRepository;

    public HomePresenter(HomeContract.View view,
                         HomeContract.Navigator navigator) {
        super(view, navigator);
    }

    @Override
    public void init() {
        super.init();
        getCurrencyNameList();
        doSearch("AED");
    }

    private void getCurrencyNameList() {
        currencyNameDisposable = currencyRepository.getCurrencyNameList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(currency -> view.showCurrencyNameList(currency));
    }

    @Override
    public void doSearch(String keyword) {
        view.showLoadingAnimation().subscribe(() -> getData(keyword));
    }

    private void getData(String keyword) {
        currencyDataDisposable = currencyRepository.getCurrencyDataList(keyword)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(currency ->
                        view.hideLoadingAnimation()
                                .subscribe(() -> view.showConversionData(currency)));
    }

    @Override
    public void destroy() {
        super.destroy();
        if (currencyNameDisposable != null && !currencyNameDisposable.isDisposed()) {
            currencyNameDisposable.dispose();
        }

        if (currencyDataDisposable != null && !currencyDataDisposable.isDisposed()) {
            currencyDataDisposable.dispose();
        }
    }
}
