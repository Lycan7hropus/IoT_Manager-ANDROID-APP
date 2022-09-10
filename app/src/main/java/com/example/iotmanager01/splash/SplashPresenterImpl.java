package com.example.iotmanager01.splash;

import android.content.Context;

import com.example.iotmanager01.api.TokenRepository;

public class SplashPresenterImpl implements SplashPresenter{
    SplashView splashView;
    TokenRepository token;

    SplashPresenterImpl(SplashView view, Context context){
        this.splashView = view;
        token = TokenRepository.getInstance(context);
    }


    @Override
    public void navigateToDestination() {

        if (token.getToken().isEmpty()) {
            splashView.openLoginView();
        } else {
            splashView.openMainPageView();
        }

    }
}
