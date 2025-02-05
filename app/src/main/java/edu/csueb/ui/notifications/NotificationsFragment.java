package edu.csueb.ui.notifications;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import edu.csueb.MainActivity;
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

        /*
             1. create the Notification Builder

             .setSmallIcon    > Display a small icon in the upper-left most corner.
             .setContentTitle > Set the title
             .setContentText  > Set the copy
             .setPriority     > Make it  a high priority and vibrate the device.
             .setVibrate      > Wait for 0 milliseconds before vibrating the device for 1000 milliseconds
             .setAutoCancel   > This makes the notification disappear when the user clicks on it.
         */
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity())
                .setSmallIcon(android.R.drawable.sym_def_app_icon)  // .setSmallIcon    > Display a small icon in the upper-left most corner.
                .setContentTitle(getString(R.string.question))      // .setContentTitle > Set the title
                .setContentText("Money")                            // .setContentText  > Set the copy
                .setPriority(NotificationCompat.PRIORITY_HIGH)      // .setPriority     > Make it  a high priority and vibrate the device.
                .setVibrate(new long[] {0, 1000})                   // .setVibrate      > Wait for 0 milliseconds before vibrating the device for 1000 milliseconds
                .setAutoCancel(true);                               // .setAutoCancel   > This makes the notification disappear when the user clicks on it.

        Intent actionIntent = new Intent(getActivity(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 0, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContent(pendingIntent);

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