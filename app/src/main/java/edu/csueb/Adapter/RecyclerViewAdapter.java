package edu.csueb.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.csueb.Model.FuelStationModel;
import edu.csueb.R;

/*
    Add a RecyclerView
    1. Define a new class ViewHolder
    2. Set the model to work with.
    3. set getItemCount() to return the model.size();
    4.
*/
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Listener listener;

    public interface Listener {
        void onClick(int position);
    }

    ArrayList<FuelStationModel> model;

    public RecyclerViewAdapter() {
        this.model = new ArrayList<>();
    }

    // Define a ViewHolder as an inner class, which tells the adapter which views to use for th data items.
    public static class ViewHolder extends RecyclerView.ViewHolder {
        //private CardView cardView;
        public TextView access_code;
        public TextView station_name;
        public TextView street_address;
        public TextView city;

        public ViewHolder(@NonNull View view) {
            super(view);
            access_code = view.findViewById(R.id.tv_access_code);
            station_name = view.findViewById(R.id.tv_station_name);
            street_address = view.findViewById(R.id.tv_street_address);
            city = view.findViewById(R.id.tv_city);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        View itemView = holder.itemView;
        FuelStationModel item = model.get(position);

        holder.access_code.setText(String.format("%s", item.getAccess_code()));
        holder.station_name.setText(String.format("%s", item.getStation_name()));
        holder.street_address.setText(String.format("%s", item.getStreet_address()));
        holder.city.setText(String.format("%s", item.getCity()));

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null) {
                    listener.onClick(position);
                }
            }
        });
    }
}