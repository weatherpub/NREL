package edu.csueb.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import edu.csueb.R;
import edu.csueb.Service.DelayedMessageService;
import edu.csueb.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        Intent intent = new Intent(getActivity(), DelayedMessageService.class);
        Log.i("log", "Inside of notificationsViewModel()");
        intent.putExtra(DelayedMessageService.EXTRA_MESSAGE, getResources().getString(R.string.response));
        getActivity().startService(intent);

        notificationsViewModel.getText().observe(getViewLifecycleOwner(), data -> {
            binding.serviceBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    binding.tvMessage.setText("Binding at work");
                    binding.serviceBtn.setText("Service Started");
                }
            });
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

// https://developer.android.com/topic/libraries/view-binding