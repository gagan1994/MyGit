package com.example.gagan.myexampleproject.fragments;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.example.gagan.myexampleproject.MainActivity;
import com.example.gagan.myexampleproject.R;
import com.example.gagan.myexampleproject.utilhelper.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends BasePagerFragment {
    public static final String TAG = "NotificationFragment";

    private Button btnPrev, btnPlayPause, btnNext;
    private TextView txt;

    @Override
    public String getTitle() {
        return "Notification";
    }

    @Override
    public String getCustomTag() {
        return TAG;
    }

    public NotificationFragment() {
        // Required empty public constructor
    }

    @OnClick(R.id.btnNotification)
    public void onClicknotification() {
        sendNotification();
    }

    private void sendNotification() {
        RemoteViews notificationView = new RemoteViews(
                getContext().getPackageName(),
                R.layout.notification_layout
        );
        notificationView.setImageViewResource(R.id.image_icon, R.drawable.ic_launcher_background);

        // Open NotificationView.java Activity
        PendingIntent pIntent = PendingIntent.getActivity(
                getContext(),
                123,
                new Intent(getActivity(), MainActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext())
                // Set Icon
                .setSmallIcon(R.drawable.ic_launcher_background)
                // Set Ticker Message
                .setTicker("")
                // Dismiss Notification
                .setAutoCancel(true)
                // Set PendingIntent into Notification
                .setContentIntent(pIntent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            // build a complex notification, with buttons and such
            //
            builder = builder.setContent(notificationView);

        } else {
            // Build a simpler notification, without buttons
            //
            builder = builder.setContentTitle(getTitle())
                    .setContentText("setContentText")
                    .setSmallIcon(android.R.drawable.ic_menu_gallery);
        }

        PendingIntent pendingPlayPause = PendingIntent.getBroadcast(getActivity(), Constant.REQ_PAUSE, Constant.getPlayPauseIntent(), 0);
        PendingIntent pendingPrev = PendingIntent.getBroadcast(getActivity(), Constant.REQ_PREV, Constant.getPrevIntent(), 0);
        PendingIntent pendingNext = PendingIntent.getBroadcast(getActivity(), Constant.REQ_NEXT, Constant.getNextIntent(), 0);

        notificationView.setOnClickPendingIntent(R.id.btnPlayPause, pendingPlayPause);
        notificationView.setOnClickPendingIntent(R.id.btnNext, pendingNext);
        notificationView.setOnClickPendingIntent(R.id.btnPrev, pendingPrev);


        NotificationManager mNotificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);

   //     builder.setOngoing(true);
        Notification notiication = builder.build();

        mNotificationManager.notify(123, notiication);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

}
