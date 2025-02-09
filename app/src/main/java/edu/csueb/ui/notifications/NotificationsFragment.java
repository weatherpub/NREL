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
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import edu.csueb.MainActivity;
import edu.csueb.MessageActivity;
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

            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel = new NotificationChannel("TransTube", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        createNotificationChannel();

        /**
         * Add Notifications to settings (this works by the way)
         */
        Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
        intent.putExtra(Settings.EXTRA_APP_PACKAGE, "Electric Vehicle App");
        intent.putExtra(Settings.EXTRA_CHANNEL_ID, getId());
        startActivity(intent);

        /*
        Intent intent = new Intent(getActivity(), MessageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE);
        */

        /* When you call getContext() from an Fragment, you will get the context of the activity in which that fragment is hosted in. */

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), "sample")
                .setAutoCancel(true)             // .setAutoCancel   > This makes the notification disappear when the user clicks on it.
                .setContentText("YouTube")
                .setContentTitle(getString(R.string.question))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setVibrate(new long[] {0, 1000}) // .setVibrate      > Wait for 0 milliseconds before vibrating the device for 1000 milliseconds
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Much longer text that cannot fit on one line, but multiple lines of text.")
                );
//                .setContentIntent(pendingIntent);

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