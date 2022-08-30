package com.example.iotmanager01.graph;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.iotmanager01.R;
import com.example.iotmanager01.splash.SplashPresenterImpl;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class GraphActivity extends AppCompatActivity implements GraphView {
    GraphPresenterImpl presenter;
    LineChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);



        presenter = new GraphPresenterImpl(this, this);




        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.type_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        chartInit();

    }

    void chartInit(){
        chart = (LineChart) findViewById(R.id.chart);

        YourData data1 = new YourData(1, 1);
        YourData data2 = new YourData(2, 2);
        YourData data3 = new YourData(3, 3);
        YourData data4 = new YourData(4, 5);
        YourData data5 = new YourData(5, 7);
        YourData data6 = new YourData(6, 5);
        YourData data7 = new YourData(7, 4);
        YourData data8 = new YourData(8, 4);
        YourData data9 = new YourData(9, 3);
        YourData data10 = new YourData(10, 6);
        YourData data11 = new YourData(11, 9);
        YourData data12 = new YourData(12, 12);


        YourData[] dataObjects = {data1, data2, data3,data4,data5,data6,data7,data8,data9,data10,data11,data12};
        List<Entry> entries = new ArrayList<Entry>();
        for (YourData data : dataObjects) {
            // turn your data into Entry objects
            entries.add(new Entry(data.getX(), data.getY()));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
//        dataSet.setColor(Color);
//        dataSet.setValueTextColor(...); // styling, ...

        LineData lineData = new LineData(dataSet);
        Log.d("TAGOWE", "onCreate: " + entries.toString());
        chart.setData(lineData);


        chart.invalidate(); // refresh
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =  getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Toast.makeText(this, "Item setting selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item2:
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                presenter.logout();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}