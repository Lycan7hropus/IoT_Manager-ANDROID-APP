package com.example.iotmanager01.api;

import android.content.Context;
import android.util.Log;

import com.example.iotmanager01.TokenRepository;
import com.example.iotmanager01.api.model.AllDataResponse;
import com.example.iotmanager01.api.model.InfoResponse;
import com.example.iotmanager01.api.model.LoginResponse;
import com.example.iotmanager01.login.RestClientCallback;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    static final String BASE_URL = "http://sandbox.ct8.pl/";
    private static final String TAG = "TAGOWE";

    RestClientCallback restClientCallback;
    ApiInterface service;

    public void start() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();




        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        service = retrofit.create(ApiInterface.class);



    }

    public void callPostLogin( JsonLoginReqest jsonBody, Callback<LoginResponse> callback){

        Call<LoginResponse> call = service.postLogin(jsonBody);

        call.enqueue(callback);
    }

    public void callGetUserInfo(String token, Callback<InfoResponse> callback){
        Call<InfoResponse> call = service.getUserInfo(token);

        call.enqueue(callback);
    }

    public void callGetALlMeasurements(String period, String id, String type, String token, Callback<List<AllDataResponse>> callback){
        if(service == null){
            Log.d(TAG, "callGetALlMeasurements: null service");
        }else{
            Call<List<AllDataResponse>> call = service.getAllMeasurements(period,id,type,token);
            call.enqueue(callback);
        }
        


    }






}
