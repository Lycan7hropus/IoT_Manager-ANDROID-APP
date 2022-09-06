package com.example.iotmanager01.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllDataResponse {

    @SerializedName("pres")
    @Expose
    public Double pres;


    @SerializedName("hum")
    @Expose
    public Double hum;

    @SerializedName("temp")
    @Expose
    public Double temp;
    @SerializedName("date_time")
    @Expose
    public String dateTime;
}
