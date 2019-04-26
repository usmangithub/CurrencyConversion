package com.currencyconversion.ui.home;

public class HomeNavigator implements HomeContract.Navigator {
    private HomeFragment fragment;
    public HomeNavigator(HomeFragment fragment) {
        this.fragment = fragment;
    }

}
