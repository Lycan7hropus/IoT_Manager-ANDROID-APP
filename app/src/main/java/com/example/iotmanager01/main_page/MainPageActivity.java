package com.example.iotmanager01.main_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.iotmanager01.R;
import com.example.iotmanager01.api.model.Sensor;
import com.example.iotmanager01.graph.GraphActivity;
import com.example.iotmanager01.login.LoginActivity;

import java.util.List;

public class MainPageActivity extends AppCompatActivity implements MainPageView{
    private MainPagePresenterImpl presenter;
    private MyAdapter adapter;
    private ListView list ;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        list = (ListView) findViewById(R.id.list_view);
        presenter = new MainPagePresenterImpl(this,this);
        progressBar = findViewById(R.id.progressBar3);

        list.setOnItemClickListener((adapterView, view, i, l) -> {
            presenter.onItemClick(adapterView,view,i,l);
        });




    }

    @Override
    public void openLoginView() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void fillListView(List<Sensor> listOfSensors) {
        progressBar.setVisibility(View.GONE);
        adapter = new MyAdapter(this, listOfSensors);
        list.setAdapter(adapter);

    }

    @Override
    public void openGraphView(String sensorName, String sensor_id) {
        Intent intent = new Intent(this, GraphActivity.class);
        intent.putExtra("SENSOR_NAME",sensorName);
        intent.putExtra("SENSOR_ID",sensor_id);
        startActivity(intent);
    }

}