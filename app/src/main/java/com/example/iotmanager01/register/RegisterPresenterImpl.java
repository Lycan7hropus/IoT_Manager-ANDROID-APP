package com.example.iotmanager01.register;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.iotmanager01.api.RestClient;
import com.example.iotmanager01.api.model.JsonRegisterRequest;
import com.example.iotmanager01.api.model.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenterImpl implements RegisterPresenter, Callback<RegisterResponse> {
    private static final String TAG = "TAGOWE_REGISTER";
    RegisterView  registerView;
    RestClient restClient;
    Context context;


    public RegisterPresenterImpl(RegisterView  registerView, Context context) {
        this.registerView = registerView;
        Log.d("TAGOWE_REGISTER", "RegisterPresenterImpl: " + this.registerView.getLogin());
        restClient = new RestClient();
        this.context =  context;

    }

    @Override
    public void registerUser() {
        JsonRegisterRequest jsonBody = new JsonRegisterRequest(registerView.getLogin(), registerView.getEmail(), registerView.getPassword(), registerView.getRePassword());
        restClient.start();
        restClient.callPostRegister(jsonBody, this);
    }


    @Override
    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
        if(response.isSuccessful()){
            if(response.body().success.booleanValue()){
                Toast.makeText(context, "Account created successfully", Toast.LENGTH_SHORT).show();
                registerView.openLoginView();
            }else{
                Toast.makeText(context, response.body().messages.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onFailure(Call<RegisterResponse> call, Throwable t) {

    }
}
