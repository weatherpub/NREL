package edu.csueb.ui.notifications;

import static android.content.Context.NOTIFICATION_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import edu.csueb.R;
import edu.csueb.Service.DelayedMessageService;
import edu.csueb.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    private void createNotificationChannel() {
        /*
         * Create the NotificationChannel, but only on API 26+
         * because the NotificationChannel class is not inn the Support Library.
         */

        Log.i("LOG", "createNotificationChannel method called");

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);

            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel("sample", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        NotificationsViewModel notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        Intent intent = new Intent(getActivity(), DelayedMessageService.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

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
//        Intent actionIntent = new Intent(getActivity(), MainActivity.class);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), "sample")
                .setAutoCancel(true)                               // .setAutoCancel   > This makes the notification disappear when the user clicks on it.
                .setContentText("YouTube")
                .setContentTitle(getString(R.string.question))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setVibrate(new long[] {0, 1000})                   // .setVibrate      > Wait for 0 milliseconds before vibrating the device for 1000 milliseconds
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Much longer text that cannot fit on one line, but multiple lines of text.")
                )
                .setContentIntent(pendingIntent);

        createNotificationChannel();

       /*
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity())
                .setSmallIcon(android.R.drawable.sym_def_app_icon)  // .setSmallIcon    > Display a small icon in the upper-left most corner.
                .setContentTitle(getString(R.string.question))      // .setContentTitle > Set the title
                .setContentText("Money")                            // .setContentText  > Set the copy
                .setPriority(NotificationCompat.PRIORITY_HIGH)      // .setPriority     > Make it  a high priority and vibrate the device.
                .setVibrate(new long[] {0, 1000})                   // .setVibrate      > Wait for 0 milliseconds before vibrating the device for 1000 milliseconds
                .setAutoCancel(true);                               // .setAutoCancel   > This makes the notification disappear when the user clicks on it.
        */

        /* Add an action to tell the notification which activity to start when clicked
         * */


        /* Issue the notification using the built-in notification service
         */

        /*                      STOPPED HERE 02-07-25 7:34PM                    */

        // NotificationManager notificationManager = getSystemService(NOTIFICATION_SERVICE);

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