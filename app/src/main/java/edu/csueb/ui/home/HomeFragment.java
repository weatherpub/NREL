package edu.csueb.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import edu.csueb.Adapter.RecyclerViewAdapter;
import edu.csueb.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        homeViewModel.getLiveData().observe(getViewLifecycleOwner(), data -> {
            binding.rvHomeFragment.setAdapter(new RecyclerViewAdapter());
            binding.rvHomeFragment.setLayoutManager(new LinearLayoutManager(getActivity()));
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}