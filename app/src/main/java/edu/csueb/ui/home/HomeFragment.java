package edu.csueb.ui.home;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import edu.csueb.Adapter.RecyclerViewAdapter;
import edu.csueb.Model.FuelStationModel;
import edu.csueb.ViewModel.FSViewModel;
import edu.csueb.databinding.FragmentHomeBinding;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private FSViewModel model;

    private RecyclerView recyclerView;

    private HashMap<String, String> url;
    private ArrayList<String> fuel_type;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter();

        // Get the ViewModel.
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        model = new ViewModelProvider(this).get(FSViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Remember the underscore is removed from the textView and the first letter after the underscore is uppercased. Located in the res/layout folder.
        // tv_home = tvHome
        final TextView access_code = binding.tvAccessCode;
        final TextView access_days_time = binding.tvAccessDaysTime;
        final TextView station_name = binding.tvStationName;
        final TextView station_address = binding.tvStreetAddress;
        final TextView city = binding.tvCity;
        final TextView textView_facility_type = binding.tvFacilityType;
        final TextView zip = binding.tvZip;
        final TextView updated_at = binding.tvUpdatedAt;

        homeViewModel.getLiveData().observe(getViewLifecycleOwner(), data -> {
            access_code.setText(data.get(0).getAccess_code());
            access_days_time.setText(data.get(0).getAccess_days_time());
            station_name.setText(data.get(0).getStation_name());
            station_address.setText(data.get(0).getStreet_address());
            city.setText(data.get(0).getCity());
            zip.setText(data.get(0).getZip());
            updated_at.setText(data.get(0).getUpdated_at());

          //  recyclerView.setAdapter(recyclerViewAdapter);
          //  recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        });

        // Update the UI only AFTER the data has changed!
        // Create an Observer which update the UI.
        final Observer<FuelStationModel> model_observer = new Observer<FuelStationModel>() {
            @Override
            public void onChanged(FuelStationModel fuelStationModel) {
                textView_facility_type.setText(fuelStationModel.getFacility_type());
            }
        };

        // Observe the LiveData, passing in this activity as the lifecycleOwner and the observer.
        model.getModel().observe(getViewLifecycleOwner(), model_observer);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}