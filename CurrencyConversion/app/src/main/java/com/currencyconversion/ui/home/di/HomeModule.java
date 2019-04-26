package com.currencyconversion.ui.home.di;

import com.currencyconversion.dagger.fragment.FragmentModule;
import com.currencyconversion.dagger.fragment.FragmentScope;
import com.currencyconversion.ui.home.HomeFragment;
import com.currencyconversion.ui.home.HomeNavigator;
import com.currencyconversion.ui.home.HomePresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule extends FragmentModule {

    private HomeFragment homeFragment;

    HomeModule(final HomeFragment homeFragment) {
        super(homeFragment);
        this.homeFragment = homeFragment;
    }

    @Provides
    @FragmentScope
    HomePresenter provideHomePresenter() {
        final HomePresenter homePresenter =
                new HomePresenter(homeFragment,
                        new HomeNavigator(homeFragment));
        homeFragment.getCategoryComponent().inject(homePresenter);
        return homePresenter;
    }
}
