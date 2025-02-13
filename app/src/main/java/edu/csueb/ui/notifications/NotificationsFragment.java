package edu.csueb.ui.notifications;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import edu.csueb.MessageActivity;
import edu.csueb.R;
import edu.csueb.Service.DelayedMessageService;
import edu.csueb.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    private void createNotificationChannel() {

        Log.i("log", "createNotificationChannel method called in: NotificationFragment");

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.notification_frag_channel_name); // Name of the notification toggle setting.
            String description = getString(R.string.notification_frag_channel_description); // Shows up at the bottom of the notification.

            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel("TransTube", name, importance);
            channel.enableLights(true);
            channel.setLightColor(Color.GREEN);
            channel.enableVibration(true);
            channel.setShowBadge(true);
            channel.setDescription(description);

            NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void sendNotification() {
        Intent intent = new Intent(getContext(), MessageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        Log.i("log", "Intent Set");

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), "notify_101")
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentTitle(getString(R.string.question))
                .setContentText("YouTube")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        Log.i("log", "NotificationCompat.Builder Set");

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getContext());

        Log.i("log", "Before: notificationManagerCompat.notify(1000, builder.build())");

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
        }

        notificationManager.notify(1000, builder.build());

        Log.i("log", "After: notificationManagerCompat.notify(1000, builder.build())");
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //  Set the notification's tap action

        NotificationsViewModel notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        createNotificationChannel();

        /**
         * Add Notifications to settings (this works by the way)
         * Open the notification channel settings.
         */
        /* commented this out 02.12.25
        Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
        intent.putExtra(Settings.EXTRA_APP_PACKAGE, getActivity().getPackageName());
        intent.putExtra(Settings.EXTRA_CHANNEL_ID, getId());
        startActivity(intent);
         */

        /*
        Intent intent = new Intent(getActivity(), MessageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE);
        */

        /* When you call getContext() from an Fragment, you will get the context of the activity in which that fragment is hosted in. */


        /* commented this out 02.12.25
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), "simple_channel_id")
                .setAutoCancel(true)             // .setAutoCancel   > This makes the notification disappear when the user clicks on it.
                .setContentText("YouTube")
                .setContentTitle(getString(R.string.question))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setVibrate(new long[] {0, 1000}) // .setVibrate      > Wait for 0 milliseconds before vibrating the device for 1000 milliseconds
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Much longer text that cannot fit on one line, but multiple lines of text.")
                );
        */
//                .setContentIntent(pendingIntent);

        notificationsViewModel.getText().observe(getViewLifecycleOwner(), data -> {
            binding.serviceBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), DelayedMessageService.class);
                    intent.putExtra(DelayedMessageService.EXTRA_MESSAGE, getResources().getString(R.string.response));
                    binding.tvMessage.setText("Binding at work");
                    binding.serviceBtn.setText("Service Started");

                    sendNotification();

                    Log.i("log", "sendNotification()");
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