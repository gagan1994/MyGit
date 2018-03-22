package com.example.gagan.myexampleproject.receivers;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.service.notification.StatusBarNotification;
import android.widget.Toast;

import com.example.gagan.myexampleproject.R;
import com.example.gagan.myexampleproject.utilhelper.Constant;

/**
 * Created by Gagan on 3/16/2018.
 */

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int status = -1;
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            StatusBarNotification[] active = mNotificationManager.getActiveNotifications();
            for (StatusBarNotification notification:active        ) {
                notification.getNotification().contentView.setImageViewResource(R.id.image_icon,R.drawable.ic_notification);
            }
        }
        if (intent.getExtras() != null) {
            status = (int) intent.getExtras().get(Constant.NotificationString);
        }
        switch (status) {
            case 0:
                displayText(context, "Prev clicked");
                break;
            case 1:
                displayText(context, "Pause/Play clicked");

                break;
            case 2:
                displayText(context, "Next clicked");

                break;
        }
        System.out.println("Received Cancelled Event");
    }

    private void displayText(Context context, String s) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
    }
}
