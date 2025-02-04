package edu.csueb.Service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class DelayedMessageService extends IntentService {

    private Timer timer;
    public static final String EXTRA_MESSAGE = "Trans Women is the EXTRA_MESSAGE";

    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "edu.csueb.Service.action.FOO";
    private static final String ACTION_BAZ = "edu.csueb.Service.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "edu.csueb.Service.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "edu.csueb.Service.extra.PARAM2";

    public DelayedMessageService() {
        super("DelayedMessageService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, DelayedMessageService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, DelayedMessageService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * onHandleIntent():
     * ...should contain the code you want to run each time the service is passed an intent.
     * It runs in a separate thread.
     * If it's passed multiple intentions, it deals with them on at a time.
     *
     * @param intent
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i("log", "Timer started...");
        startTimer();

        /*
        synchronized (this) {
            try {
                wait(10000);
                Log.i("log", "* * * * * * * * * * * * * * * * * * * * ");
                Log.i("log", "Service Message after 10000 milliseconds");
                Log.i("log", "* * * * * * * * * * * * * * * * * * * * ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        */
    }


    private int numberOfSeconds() {
        return (1000 * 10);
    }

    private void startTimer() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Log.i("log", "* * * * * * * * * * * * * * * * * * * * ");
                Log.i("log", "Service Message after 10000 milliseconds");
                Log.i("log", "* * * * * * * * * * * * * * * * * * * * ");
            }
        };

        timer = new Timer(true);
        int delay = numberOfSeconds();
        int interval = numberOfSeconds();
        timer.schedule(task, delay, interval);
    }
}