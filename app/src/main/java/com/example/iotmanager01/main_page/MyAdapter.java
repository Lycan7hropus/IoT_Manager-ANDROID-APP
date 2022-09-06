package com.example.iotmanager01.main_page;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.iotmanager01.R;
import com.example.iotmanager01.api.model.Sensor;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {
    private Context context; //context
    private List<Sensor> items; //data source of the list adapter

    public MyAdapter(Context context, List<Sensor> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view ==null)
        {
            view = LayoutInflater.from(context).
                    inflate(R.layout.list_row, viewGroup, false);
        }

        Sensor currentItem = (Sensor) getItem(i);

        TextView textViewItemName = (TextView)
                view.findViewById(R.id.Row);

        textViewItemName.setText(currentItem.getSensorName());

        return view;
    }


}
