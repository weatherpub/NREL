package edu.csueb.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.csueb.Model.FuelStationModel;

/*
    Add a RecyclerView
    1. Define a new class ViewHolder
    2. Set the model to work with.
    3. set getItemCount() to return the model.size();
    4.
*/
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<FuelStationModel> model;

    public RecyclerViewAdapter() {
        this.model = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    // Define a ViewHolder as an inner class, which tells the adapter which views to use for th data items.
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView category;

        public ViewHolder(@NonNull View view) {
            super(view);
            category = (TextView) view.findViewById(R.id.tv_category);
        }
    }
}