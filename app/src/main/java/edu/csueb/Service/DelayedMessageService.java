package edu.csueb.Service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DelayedMessageService extends IntentService {

    private final OkHttpClient client;
    private Timer timer;
    public static final String EXTRA_MESSAGE = "Trans Women is the EXTRA_MESSAGE";

    public DelayedMessageService() {
        super("DelayedMessageService");
         client = new OkHttpClient();
    }

    /**
     * onHandleIntent(): should contain the code you want to run each time the service is passed an intent.
     * It runs in a separate thread.
     * (If it's passed multiple intentions, it deals with them on at a time.)
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        startTimer();
    }

    private int seconds() {
        return (6000 * 10); // 60 seconds
    }

    // All long running code should be placed within startTimer()
    private void startTimer() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Log.i("log", "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
                Log.i("log", "* Delayed Message Service - Service Started in onCreate() *");
                Log.i("log", "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");

                Request request = new Request.Builder().url("https://api.weather.gov/openapi.json").build();

                try(Response response = client.newCall(request).execute()) {
                    if(!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                    Headers responseHeaders = response.headers();

                    for(int i = 0; i < responseHeaders.size(); i++) {
                        Log.i("log", "-> " + responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }

                    Log.i("log", "Response => " + response.body().string());

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        timer = new Timer(true);
        int delay = seconds();
        int interval = seconds();
        timer.schedule(task, delay, interval);
    }
}