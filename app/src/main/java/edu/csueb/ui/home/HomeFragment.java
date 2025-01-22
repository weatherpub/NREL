package edu.csueb.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import edu.csueb.Model.FuelStationModel;
import edu.csueb.ViewModel.FSViewModel;
import edu.csueb.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private FSViewModel model;

    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        model = new ViewModelProvider(this).get(FSViewModel.class);

        /*
        FSViewModel fsViewModel = new ViewModelProvider(this).get(FSViewModel.class);
        fsViewModel.getModel().observe(getViewLifecycleOwner(), model -> {
            Log.i("fs", "getModel().observe()");
        });
        */

        Log.i("fs", "HomeFragment() -> onCreateView()");

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Remember the underscore is removed from the textView and the first letter after the underscore is uppercased. Located in the res/layout folder.
        // tv_home = tvHome
        final TextView access_code = binding.tvAccessCode;
        final TextView access_days_time = binding.tvAccessDaysTime;
        final TextView station_name = binding.tvStationName;
        final TextView station_address = binding.tvStreetAddress;
        final TextView city = binding.tvCity;
        final TextView facility_type = binding.tvFacilityType;

        int i = 0;

        homeViewModel.getLiveData().observe(getViewLifecycleOwner(), data -> {
            // for(int i = 0; i < data.size(); i++) {
                access_code.setText(data.get(i).getAccess_code());
                access_days_time.setText(data.get(i).getAccess_days_time());
                station_name.setText(data.get(i).getStation_name());
                station_address.setText(data.get(i).getStreet_address());
                city.setText(data.get(i).getCity());
            // }
        });

        // Update the UI only AFTER the data has changed!
        final Observer<FuelStationModel> model_observer = new Observer<FuelStationModel>() {
            @Override
            public void onChanged(FuelStationModel fuelStationModel) {
                facility_type.setText(fuelStationModel.getFacility_type());
            }
        };

        // Observe the LiveData, passing in this activity as the lifecycleOwner and the observer.
        model.getModel().observe(getViewLifecycleOwner(), model_observer);

        // recyclerView = view.findViewById(R.id.id_recycler_view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}