package com.example.iotmanager01.main_page;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iotmanager01.R;
import com.example.iotmanager01.api.model.Sensor;

import java.util.List;

public class MpSensorAdapter extends
        RecyclerView.Adapter<MpSensorAdapter.ViewHolder> {

    private final List<Sensor> sensorList;
    private OnNoteListener onNoteListener;

    public MpSensorAdapter(List<Sensor> sensorList, OnNoteListener onNoteListener) {
        this.sensorList = sensorList;
        this.onNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // Inflate the custom layout
        View view = inflater.inflate(R.layout.main_page_recycler_row, parent, false);
        // Return a new holder instance
        return new ViewHolder(view, this.onNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameTextView.setText(sensorList.get(position).getSensorName());
        if (sensorList.get(0).getIsOwner().equals("false")) {
            holder.imageView.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return sensorList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView nameTextView;
        public ImageView imageView;
        public CardView cardView;
        OnNoteListener onNoteListener;


        public ViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardViewMPRV) ;
            nameTextView = (TextView) itemView.findViewById(R.id.mp_row_sensor_name);
            imageView = (ImageView) itemView.findViewById(R.id.mp_row_img);
            this.onNoteListener = onNoteListener;

            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener {
        void onNoteClick(int position);
    }
}
