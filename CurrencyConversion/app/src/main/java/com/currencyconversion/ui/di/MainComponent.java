package com.currencyconversion.ui.di;

import com.currencyconversion.dagger.activity.ActivityModule;
import com.currencyconversion.dagger.activity.ActivityScope;
import com.currencyconversion.dagger.application.ApplicationComponent;
import com.currencyconversion.ui.MainActivity;
import com.currencyconversion.ui.MainNavigator;
import com.currencyconversion.ui.MainPresenter;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = {
                MainModule.class
        }
)
public interface MainComponent extends ActivityModule.Exposes {

    void inject(MainActivity activity);

    void inject(MainPresenter presenter);

    void inject(MainNavigator navigator);

    final class Initializer {

        private Initializer() {
        }

        public static MainComponent init(final MainActivity mainActivity,
                                         final ApplicationComponent applicationComponent) {
            return DaggerMainComponent.builder()
                    .applicationComponent(applicationComponent)
                    .mainModule(new MainModule(mainActivity))
                    .build();
        }
    }
}
