package com.example.iotmanager01.login;

import android.content.Context;
import android.util.Log;

import com.example.iotmanager01.api.TokenRepository;
import com.example.iotmanager01.api.model.JsonLoginRequest;
import com.example.iotmanager01.api.model.LoginResponse;
import com.example.iotmanager01.api.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenterImpl implements LoginPresenter, Callback<LoginResponse> {
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

        JsonLoginRequest jsonBody = new JsonLoginRequest(loginView.getLogin(), loginView.getPassword());

        restClient.start();
        restClient.callPostLogin(jsonBody, this);

    }

    @Override
    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
        if(response.isSuccessful()) {
            if(response.body().getStatus().equals("success")){
                tokenRepository.setToken(response.body().getToken());
                loginView.openMainPageView();
            }else{
                loginView.errorView(response.body().getStatus().toString());
            }

        } else {
            loginView.errorView("response unsuccessful");
        }
    }

    @Override
    public void onFailure(Call<LoginResponse> call, Throwable t) {
        Log.d(TAG, "onFailure: " + call.toString());
        loginView.errorView("onfailure");
    }
}
