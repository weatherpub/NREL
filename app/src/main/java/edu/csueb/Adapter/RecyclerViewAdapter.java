package edu.csueb.Adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.csueb.Model.FuelStationModel;
import edu.csueb.Pattern.Singleton.FuelStationViewModel;
import edu.csueb.R;

/*
    Add a RecyclerView
    0. Create a Card View Layout file in res folder: card_view.xml
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

    // Tell the Adapter what data to work with: FuelStationModel
    ArrayList<FuelStationModel> model;

    // Get data from the HomeViewModel
    public RecyclerViewAdapter() {
        FuelStationViewModel fuelStationViewModel = FuelStationViewModel.getInstance();
        model = fuelStationViewModel.getData();
    }

    /*
        Define a ViewHolder as an inner class, which tells the adapter which views to use for the data items.

        [Head First Android Page: 548]
        The ViewHolder is used to define what view or views the RecyclerView should use for each data item it's given.
    */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        //private CardView cardView;
        public TextView access_code;
        public TextView station_name;
        public TextView street_address;
        public TextView city;

        // Original Code
        public ViewHolder(@NonNull View view) {
        /**
         *
         * @param view
         */
        // public ViewHolder(@NonNull CardView view) {
            super(view);
            access_code = view.findViewById(R.id.tv_access_code);
            station_name = view.findViewById(R.id.tv_station_name);
            street_address = view.findViewById(R.id.tv_street_address);
            city = view.findViewById(R.id.tv_city);
        }
    }

    /**
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        // CardView view = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new ViewHolder(view);
    }

    /**
     *
     * @return model.size()
     */
    @Override
    public int getItemCount() {
        Log.i("LOG", "Model Size: " + model.size());
        return model.size();
    }

    /**
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        View itemView = holder.itemView;
        FuelStationModel item = model.get(position);

        holder.access_code.setText(String.format("%s", item.getAccess_code()));
        holder.station_name.setText(String.format("%s", item.getStation_name()));
        holder.street_address.setText(String.format("%s", item.getStreet_address()));
        holder.city.setText(String.format("%s", item.getCity()));

        itemView.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param view
             */
            @Override
            public void onClick(View view) {
                if(listener != null) {
                    listener.onClick(position);
                }
            }
        });
    }
}