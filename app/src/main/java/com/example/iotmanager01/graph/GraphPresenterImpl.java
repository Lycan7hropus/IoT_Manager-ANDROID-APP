package com.example.iotmanager01.graph;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.iotmanager01.R;
import com.example.iotmanager01.api.TokenRepository;
import com.example.iotmanager01.api.RestClient;
import com.example.iotmanager01.api.model.AllDataResponse;
import com.example.iotmanager01.graph.graph_extras.Measurement;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GraphPresenterImpl implements GraphPresenter, Callback<List<AllDataResponse>>, AdapterView.OnItemSelectedListener {
    private static final String TAG = "TAGOWE";
    GraphView graphView;
    TokenRepository token;
    RestClient restClient;
    Context context;
    ArrayAdapter<CharSequence> adapter;
    List<AllDataResponse> myData;
    String dataType = "Temperature";
    Measurement measurement;

    GraphPresenterImpl(GraphView view, Context context) {
        graphView = view;
        token = TokenRepository.getInstance(context);
        this.context = context;
        restClient = new RestClient();
        measurement =  new Measurement(0,0,0);

        getData();
        spinnerInit();

        Observable<Long> timeObservable = Observable
                .timer(10, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        delayedFunctionExecutor(timeObservable);


    }

    void delayedFunctionExecutor(Observable<Long> timeObservable){
        timeObservable.subscribe(new Observer<Long>() {
        
            long time = 0; // variable for demonstating how much time has passed

            @Override
            public void onSubscribe(Disposable d) {
                time = System.currentTimeMillis() / 1000;
                //Log.d("TAGOWE2", "onSubscirbe: ");
                callRestApi("LAST", graphView.getSensorId(),"all");
            }
            @Override
            public void onNext(Long aLong) {
                //Log.d("TAGOWE2", "onNext: " + ((System.currentTimeMillis() / 1000) - time) + " seconds have elapsed." );
            }
            @Override
            public void onError(Throwable e) {

            }
            @Override
            public void onComplete() {
                callRestApi("LAST", graphView.getSensorId(),"all");
                Log.d(TAG, "onComplete: delayed");
            }
        });
    }

    void callRestApi(String period, String sensorId, String type) {

        Log.d(TAG, "callRestApi: " + dataType + graphView.getSensorId() + graphView.getPeriod());
        restClient.start();
        restClient.callGetALlMeasurements(period, graphView.getSensorId(), type, token.getToken(), this);

    }


    void chartInit() {

        List<Entry> entries = new ArrayList<>();

        int i = 0;
        float sum = 0;
        //CONVERT STRING DATA INTO MLS SINCE 1970
        for (AllDataResponse data : myData) {


            String myDate = data.dateTime;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;


            try {
                date = sdf.parse(myDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }


            long millis = (date.getTime() / 1000) - 1_640_995_200;


            switch (dataType) {
                case "Humidity":
                    if (i < 10) {
                        sum = sum + data.hum.floatValue();
                        i++;
                    } else {
                        float avg = sum / i;
                        entries.add(new Entry(millis, avg));
                        i = 0;
                        sum = 0;

                    }
                    break;
                case "Pressure":
                    if (i < 10) {
                        sum = sum + data.pres.floatValue();
                        i++;
                    } else {
                        float avg = sum / i;
                        entries.add(new Entry(millis, avg));
                        i = 0;
                        sum = 0;

                    }
                    break;
                default:
                    if (i < 10) {
                        sum = sum + data.temp.floatValue();
                        i++;
                    } else {
                        float avg = sum / i;
                        entries.add(new Entry(millis, avg));
                        i = 0;
                        sum = 0;

                    }
                    break;


            }


        }
        Log.d(TAG, "chartInit: INIT");

        //ENTRIES REQUIRE ASCENDING DATE VALUES, LETS SORT IT
        entries.sort(new Comparator<Entry>() {
            @Override
            public int compare(Entry entry, Entry t1) {
                if (entry.getX() > t1.getX()) {
                    return 1;
                } else if (entry.getX() < t1.getX()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });


        LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
        dataSet.setColor(Color.RED);
        dataSet.setValueTextColor(Color.GREEN);

        LineData lineData = new LineData(dataSet);

        graphView.populateChart(lineData);

    }


    @Override
    public void logout() {
        token.clear();
        graphView.openLoginView();

    }

    @Override
    public void getData() {
        callRestApi(graphView.getPeriod(), graphView.getSensorId(),"all");
    }

    @Override
    public void spinnerInit() {
        // Create an ArrayAdapter using the string array and a default spinner layout
        adapter = ArrayAdapter.createFromResource(context,
                R.array.type_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        graphView.populateSpinner(adapter);

    }

    @Override
    public void onResponse(Call<List<AllDataResponse>> call, Response<List<AllDataResponse>> response) {
        if (response.isSuccessful()) {

           if(!response.body().isEmpty()){
               if(response.body().size()>1){
                   myData = response.body();
                   chartInit();
               }else{
                   measurement.setData(response.body().get(0).temp.floatValue(),response.body().get(0).hum.floatValue(),response.body().get(0).pres.floatValue());
                   Log.d(TAG, "onResponse: " + measurement.print());
                   graphView.updateCurrentMeasurments(response.body().get(0).temp.toString(),response.body().get(0).hum.toString(),response.body().get(0).pres.toString());
               }
           }else {
               Log.d(TAG, "onResponse: empty");
           }



        } else {
            Log.d(TAG, "Un onResponse: " + response.message());
        }
    }

    @Override
    public void onFailure(Call<List<AllDataResponse>> call, Throwable t) {

    }


    //SPINNER
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (myData != null) {
            dataType = adapter.getItem(i).toString();
            chartInit();
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
