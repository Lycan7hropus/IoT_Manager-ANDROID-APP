package com.example.iotmanager01.api;

import android.content.Context;
import android.util.Log;

import com.example.iotmanager01.TokenRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient implements Callback<LoginResponse> {
    static final String BASE_URL = "http://sandbox.ct8.pl/";
    private static final String TAG = "TAGOWE";
    TokenRepository token;

    public void start(Context context, JsonLoginReqest jsonBody) {

        token = TokenRepository.getInstance(context);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface service = retrofit.create(ApiInterface.class);



        Call<LoginResponse> call = service.postJson(jsonBody);


        call.enqueue(this);

    }


    @Override
    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
        if(response.isSuccessful()) {
            token.setToken(response.body().token);
            Log.d(TAG, "S onResponse: " + token.getToken());
            Log.d(TAG, "S onResponse: " + response.body().status);

        } else {
            Log.d(TAG, "E onResponse: " + response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<LoginResponse> call, Throwable t) {
        Log.d(TAG, "onFailure: " + t.toString());
    }
}
