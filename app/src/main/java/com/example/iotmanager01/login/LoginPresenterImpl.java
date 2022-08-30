package com.example.iotmanager01.login;

import android.content.Context;
import android.util.Log;

import com.example.iotmanager01.TokenRepository;
import com.example.iotmanager01.api.JsonLoginReqest;
import com.example.iotmanager01.api.RestClient;

public class LoginPresenterImpl implements LoginPresenter{
    private static final String TAG = "TAGOWE";
    LoginView loginView;
    TokenRepository tokenRepository;
    RestClient restClient;
    Context context;

    LoginPresenterImpl(LoginView view, Context context){
        this.loginView = view;
        this.tokenRepository =  TokenRepository.getInstance(context);
        this.context =  context;
        restClient = new RestClient();

    }


    @Override
    public void loginUser() {

        JsonLoginReqest jsonBody = new JsonLoginReqest(loginView.getLogin(), loginView.getPassword());

        restClient.start(context, jsonBody);
        Log.d(TAG, "loginUser: " + tokenRepository.getToken().toString());

    }
}
