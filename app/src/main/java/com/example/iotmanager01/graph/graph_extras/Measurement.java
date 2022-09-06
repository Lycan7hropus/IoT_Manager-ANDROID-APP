package com.example.iotmanager01.graph.graph_extras;

public class Measurement {

    float temperature, humidity, pressure;

    public Measurement(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public void setData(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public String print(){
        return "T: "+ String.valueOf(temperature) + " H: " + String.valueOf(humidity) + " P: " + String.valueOf(pressure);
    }
}
