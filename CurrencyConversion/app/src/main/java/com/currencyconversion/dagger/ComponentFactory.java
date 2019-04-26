package com.currencyconversion.dagger;

import com.currencyconversion.TestApplication;
import com.currencyconversion.dagger.application.ApplicationComponent;

public final class ComponentFactory {
    private ComponentFactory() {
    }

    public static ApplicationComponent createApplicationComponent(
            final TestApplication testApplication) {
        return ApplicationComponent.Initializer.init(testApplication);
    }
}
