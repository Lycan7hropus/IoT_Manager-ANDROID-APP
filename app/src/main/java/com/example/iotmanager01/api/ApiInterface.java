package com.example.iotmanager01.api;

import com.example.iotmanager01.api.model.AllDataResponse;
import com.example.iotmanager01.api.model.InfoResponse;
import com.example.iotmanager01.api.model.JsonLoginRequest;
import com.example.iotmanager01.api.model.JsonRegisterRequest;
import com.example.iotmanager01.api.model.LoginResponse;
import com.example.iotmanager01.api.model.RegisterResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("api/mobile_app_rest/user_login.php")
    Call<LoginResponse> postLogin(@Body JsonLoginRequest body);

    @GET("api/mobile_app_rest/user_info.php")
    Call<InfoResponse> getUserInfo(@Header("Authorization") String authHeader);

    @GET("api/mobile_app_rest/data_get.php")
    Call<List<AllDataResponse>> getAllMeasurements(@Query("perioid") String period, @Query("id") String id, @Query("type") String type, @Header("Authorization") String authHeader);

    @POST("api/mobile_app_rest/user_registration.php")
    Call<RegisterResponse> postRegister(@Body JsonRegisterRequest body);


}
