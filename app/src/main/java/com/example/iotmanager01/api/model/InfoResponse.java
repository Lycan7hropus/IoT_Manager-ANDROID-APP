package com.example.iotmanager01.api.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InfoResponse {

    @SerializedName("sensors")
    @Expose
    public List<Sensor> sensors = null;


}