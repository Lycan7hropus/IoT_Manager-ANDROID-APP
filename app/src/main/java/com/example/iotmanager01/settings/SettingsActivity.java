package com.example.iotmanager01.settings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.iotmanager01.R;
import com.example.iotmanager01.api.model.Sensor;
import com.example.iotmanager01.login.LoginActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {
    ArrayList<Sensor> sensors ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent intent = getIntent();
        String msg = intent.getStringExtra("SENSORS");

        Type listOfMyClassObject = new TypeToken<ArrayList<Sensor>>() {}.getType();
        Gson gson = new Gson();
        sensors = gson.fromJson(msg, listOfMyClassObject);

        Toast.makeText(getApplicationContext(), sensors.get(1).getSensorName().toString(), Toast.LENGTH_SHORT).show();

        // Lookup the recyclerview in activity layout
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvSensors);

        //sensors = SensorModel.createSensorList();
        // Initialize contacts

        // Create adapter passing in the sample user data
        SensorAdapter adapter = new SensorAdapter(sensors);
        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        // That's all!



    }


}