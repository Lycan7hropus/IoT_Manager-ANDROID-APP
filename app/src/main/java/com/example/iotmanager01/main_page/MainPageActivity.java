package com.example.iotmanager01.main_page;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.iotmanager01.MainActivity;
import com.example.iotmanager01.R;
import com.example.iotmanager01.api.model.Sensor;
import com.example.iotmanager01.graph.GraphActivity;
import com.example.iotmanager01.login.LoginActivity;
import com.example.iotmanager01.settings.SettingsActivity;
import com.google.gson.Gson;

import java.util.List;

public class MainPageActivity extends AppCompatActivity implements MainPageView, MpSensorAdapter.OnNoteListener {
    private MainPagePresenterImpl presenter;
    private MyAdapter adapter;
    private RecyclerView recyclerView ;
    ProgressBar progressBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        //list = (ListView) findViewById(R.id.list_view);
        presenter = new MainPagePresenterImpl(this,this);
        progressBar = findViewById(R.id.progressBar3);
        recyclerView =  findViewById(R.id.mp_rv);

    }

    @Override
    public void openLoginView() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void fillRecyclerView(List<Sensor> listOfSensors) {
        progressBar.setVisibility(View.GONE);
        MpSensorAdapter adapter = new MpSensorAdapter(listOfSensors, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }



    @Override
    public void openGraphView(String sensorName, String sensor_id) {
        Intent intent = new Intent(this, GraphActivity.class);
        intent.putExtra("SENSOR_NAME",sensorName);
        intent.putExtra("SENSOR_ID",sensor_id);
        startActivity(intent);
    }

    @Override
    public void openSettingsView(List<Sensor> listOfSensor) {
        Intent intent = new Intent(this, SettingsActivity.class);

        String listSerializedToJson = new Gson().toJson(listOfSensor);

        intent.putExtra("SENSORS", listSerializedToJson);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                presenter.openSettings();
                break;
            case R.id.item2:
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                presenter.logout();
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onNoteClick(int position) {
        presenter.openMainPange(position);
    }
}