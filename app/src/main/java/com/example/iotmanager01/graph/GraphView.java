package com.example.iotmanager01.graph;

import android.widget.ArrayAdapter;

import com.github.mikephil.charting.data.LineData;

public interface GraphView {
    void openLoginView();
    void populateChart(LineData lineData);
    void populateSpinner( ArrayAdapter<CharSequence> adapter);
    String getDataType();
    String getPeriod();
    String getSensorId();
    void updateCurrentMeasurments(String tempText, String humText, String presText);
}
