package edu.csueb.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import edu.csueb.Adapter.CocktailRecyclerViewAdapter;
import edu.csueb.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        DashboardViewModel dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        dashboardViewModel.getLiveData().observe(getViewLifecycleOwner(), data-> {
            binding.rvDashboardFragment.setAdapter(new CocktailRecyclerViewAdapter());
            binding.rvDashboardFragment.setLayoutManager(new LinearLayoutManager(getActivity()));
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}