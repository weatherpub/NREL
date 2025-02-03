package edu.csueb.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import edu.csueb.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        notificationsViewModel.getText().observe(getViewLifecycleOwner(), data -> {
            binding.serviceBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    binding.tvMessage.setText("Binding at work");
                    binding.serviceBtn.setText("Service Started");
                    /*
                    Intent intent = new Intent(getActivity(), DashboardViewModel.class);
                    intent.putExtra(DelayedMessageService.EXTRA_MESSAGE, getResources().getString(R.string.app_name));
                    startActivity(intent);
                    */
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