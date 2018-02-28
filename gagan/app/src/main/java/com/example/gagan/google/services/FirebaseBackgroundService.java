package com.example.gagan.google.services;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

/**
 * Created by Gagan on 2/16/2018.
 */

public class FirebaseBackgroundService extends WakefulBroadcastReceiver {

    private static final String TAG = "FirebaseBackground";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Notification came");


    }
}