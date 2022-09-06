package com.example.iotmanager01.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sensor {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("sensor_id")
    @Expose
    private String sensorId;
    @SerializedName("sensor_name")
    @Expose
    private String sensorName;
    @SerializedName("user_login")
    @Expose
    private String userLogin;
    @SerializedName("is_owner")
    @Expose
    private String isOwner;

    /**
     * No args constructor for use in serialization
     *
     */
    public Sensor() {
    }

    /**
     *
     * @param userLogin
     * @param sensorName
     * @param isOwner
     * @param id
     * @param sensorId
     */
    public Sensor(String id, String sensorId, String sensorName, String userLogin, String isOwner) {
        super();
        this.id = id;
        this.sensorId = sensorId;
        this.sensorName = sensorName;
        this.userLogin = userLogin;
        this.isOwner = isOwner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getIsOwner() {
        return isOwner;
    }

    public void setIsOwner(String isOwner) {
        this.isOwner = isOwner;
    }

}