package com.example.iotmanager01.graph;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iotmanager01.R;
import com.example.iotmanager01.graph.graph_extras.LineChartXAxisValueFormatter;
import com.example.iotmanager01.login.LoginActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.google.android.material.button.MaterialButtonToggleGroup;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GraphActivity extends AppCompatActivity implements GraphView {
    GraphPresenterImpl presenter;
    LineChart chart;
    Spinner spinner;
    ProgressBar progressBar;
    MaterialButtonToggleGroup toggleButton;
    TextView sensor_text_view, tempMes, humMes, presMes;
    Button btn24H, btn1H, btn1W, btn1M;
    String sensor_id;
    String period;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        sensor_text_view = findViewById(R.id.sensor_text_view);
        tempMes = findViewById(R.id.tempMes);
        humMes = findViewById(R.id.humMes);
        presMes = findViewById(R.id.presMes);
        progressBar = findViewById(R.id.progressBar);
        chart = (LineChart) findViewById(R.id.chart);
        spinner = (Spinner) findViewById(R.id.spinner);
        toggleButton = findViewById(R.id.toggleButton);
        btn1H = findViewById(R.id.btn1H);
        btn24H = findViewById(R.id.btn24H);
        btn1W = findViewById(R.id.btn1W);
        btn1M = findViewById(R.id.btn1M);
        toggleButton.check(R.id.btn24H);





        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if (b != null) {
            String sensor_name = (String) b.get("SENSOR_NAME");
            sensor_id = (String) b.get("SENSOR_ID");
            sensor_text_view.setText(sensor_name);

        }




        presenter = new GraphPresenterImpl(this, this);

        toggleButton.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                presenter.getData();
                Log.d("TAGOWE", "onButtonChecked: ");
                progressBar.setVisibility(View.VISIBLE);

            }
        });



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
                Toast.makeText(this, "Item setting selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item2:
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                presenter.logout();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void openLoginView() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void populateChart(LineData lineData) {
        chart.setData(lineData);
        chart.getXAxis().setValueFormatter(new LineChartXAxisValueFormatter());
        progressBar.setVisibility(View.GONE);
        chart.invalidate(); // refresh

    }

    @Override
    public void populateSpinner(ArrayAdapter<CharSequence> adapter) {
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                presenter.onItemSelected(adapterView, view, i, l);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(), "NOTHING", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public String getDataType() {

        return "Pressure";
    }


    @Override
    public String getPeriod() {


        switch (toggleButton.getCheckedButtonId()){
            case R.id.btn1H:
                period = "HOUR";
                break;
            case R.id.btn24H:
                period = "DAY";
                break;
            case R.id.btn1W:
                period = "WEEK";
                break;
            case R.id.btn1M:
                period = "MONTH";
                break;
            default:
                period = "HOUR";
        }

        if(period != null){
            return period;
        }else {
            return "1H";
        }

    }

    @Override
    public String getSensorId() {
        return sensor_id;
    }

    @Override
    public void updateCurrentMeasurments(String tempText, String humText, String presText) {
        tempMes.setText(tempText);
        humMes.setText(humText);
        presMes.setText(presText);
    }
}