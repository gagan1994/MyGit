package com.example.gagan.google.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.gagan.google.MainActivity;

import org.json.JSONObject;

/**
 * Created by Gagan on 2/15/2018.
 */

public class Utils {

    public static boolean CheckPhoneValid(String phone) {
        int length = phone.length();
        if (!TextUtils.isEmpty(phone) && phone.length() == 10) {
            return true;
        }
        return false;
    }

    public static void showSnacbar(Context context, View parentView, String snackBarDisplayText,
                                   String actionText, int color, @Nullable View.OnClickListener myOnClickListener) {
        if (myOnClickListener == null) {
            myOnClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            };
        }

// Pass in the click listener when displaying the Snackbar
        Snackbar snackbar = Snackbar.make(parentView, snackBarDisplayText, Snackbar.LENGTH_LONG)
                .setAction(actionText, myOnClickListener);
// Don’t forget to show!

        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(color);
        //snackbarView.setAlpha(0.4f);
        snackbar.show();

    }

    public static void Toast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void Log(String tag, String message) {
        Log.d(tag, message);
    }

    public static boolean checkIsEmpty(String[] strings) {
        for (String s : strings) {
            if (TextUtils.isEmpty(s)) {
                return true;
            }
        }
        return false;
    }

    public static void setNotificationData(Context applicationContext, JSONObject data) {

    }

    public static boolean hasPermissions(Context context, String[] permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
}
