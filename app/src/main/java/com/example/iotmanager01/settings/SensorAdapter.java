package com.example.iotmanager01.settings;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iotmanager01.R;
import com.example.iotmanager01.api.model.Sensor;

import java.util.ArrayList;
import java.util.List;

public class SensorAdapter extends RecyclerView.Adapter<SensorAdapter.ViewHolder> {
    private List<Sensor> sensorModelList;

    // Pass in the contact array into the constructor
    public SensorAdapter(ArrayList<Sensor> sensors) {
        sensorModelList = sensors;
    }




    @NonNull
    @Override
    public SensorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.recycler_row, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SensorAdapter.ViewHolder holder, int position) {
            holder.idTextView.setText(String.valueOf(sensorModelList.get(position).getSensorId()));
            holder.nameTextView.setText(sensorModelList.get(position).getSensorName());
    }

    @Override
    public int getItemCount() {
        return sensorModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public TextView idTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.nameTextView);
            idTextView = itemView.findViewById(R.id.idTextView);

        }
    }
}
