package com.example.iotmanager01.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RegisterResponse {
    @SerializedName("success")
    @Expose
    public Boolean success;
    @SerializedName("messages")
    @Expose
    public List<String> messages = null;

}
