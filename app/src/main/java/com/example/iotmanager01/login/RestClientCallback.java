package com.example.iotmanager01.login;

import com.example.iotmanager01.api.model.LoginResponse;

import retrofit2.Call;
import retrofit2.Response;

public interface RestClientCallback {
     void onResponse(Call<LoginResponse> call, Response<LoginResponse> response);
    public void onFailure(Call<LoginResponse> call, Throwable t);
}
