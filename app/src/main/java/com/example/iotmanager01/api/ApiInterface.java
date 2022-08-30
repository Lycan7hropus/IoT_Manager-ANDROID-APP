package com.example.iotmanager01.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("/api/mobile_app_rest/user_login.php")
    Call<LoginResponse> postJson(@Body JsonLoginReqest body);


}
