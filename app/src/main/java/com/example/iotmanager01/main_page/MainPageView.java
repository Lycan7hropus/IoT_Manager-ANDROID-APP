package com.example.iotmanager01.main_page;

import com.example.iotmanager01.api.model.Sensor;

import java.util.List;

public interface MainPageView {
    void openLoginView();
    void fillListView(List<Sensor> listOfSensors);
    void openGraphView(String sensorName, String sensor_id);
}
