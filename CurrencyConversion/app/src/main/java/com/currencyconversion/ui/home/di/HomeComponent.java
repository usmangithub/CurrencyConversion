package com.currencyconversion.ui.home.di;

import com.currencyconversion.dagger.application.ApplicationComponent;
import com.currencyconversion.dagger.fragment.FragmentModule;
import com.currencyconversion.dagger.fragment.FragmentScope;
import com.currencyconversion.ui.home.HomeFragment;
import com.currencyconversion.ui.home.HomeNavigator;
import com.currencyconversion.ui.home.HomePresenter;

import dagger.Component;

@FragmentScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = {
                HomeModule.class
        }
)
public interface HomeComponent extends FragmentModule.Exposes {

    void inject(HomeFragment fragment);

    void inject(HomePresenter presenter);

    void inject(HomeNavigator navigator);

    final class Initializer {

        private Initializer() {
        }

        public static HomeComponent init(final HomeFragment homeFragment,
                                         final ApplicationComponent applicationComponent) {
            return DaggerHomeComponent.builder()
                    .applicationComponent(applicationComponent)
                    .homeModule(new HomeModule(homeFragment))
                    .build();
        }
    }
}
