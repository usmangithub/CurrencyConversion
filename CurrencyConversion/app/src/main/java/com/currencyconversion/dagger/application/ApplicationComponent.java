package com.currencyconversion.dagger.application;

import com.currencyconversion.TestApplication;
import com.currencyconversion.dagger.application.module.ApplicationModule;
import com.currencyconversion.dagger.application.module.DataModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, DataModule.class})

public interface ApplicationComponent extends ApplicationComponentInjects, ApplicationComponentExposes {
    final class Initializer {
        static public ApplicationComponent init(final TestApplication testApplication) {
            return DaggerApplicationComponent
                    .builder()
                    .applicationModule(new ApplicationModule(testApplication))
                    .dataModule(new DataModule())
                    .build();
        }

        private Initializer() {
        }
    }
}
