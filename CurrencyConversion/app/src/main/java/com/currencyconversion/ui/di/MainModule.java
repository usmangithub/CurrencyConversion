package com.currencyconversion.ui.di;

import com.currencyconversion.dagger.activity.ActivityModule;
import com.currencyconversion.dagger.activity.ActivityScope;
import com.currencyconversion.ui.MainActivity;
import com.currencyconversion.ui.MainNavigator;
import com.currencyconversion.ui.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule extends ActivityModule {

    private MainActivity mainActivity;

    MainModule(final MainActivity mainActivity) {
        super(mainActivity);
        this.mainActivity = mainActivity;
    }

    @Provides
    @ActivityScope
    MainPresenter provideMainPresenter() {
        final MainPresenter mainPresenter =
                new MainPresenter(mainActivity,
                        new MainNavigator(mainActivity));
        mainActivity.getMainComponent().inject(mainPresenter);
        return mainPresenter;
    }
}
