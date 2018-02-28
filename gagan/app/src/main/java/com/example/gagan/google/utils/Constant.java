package com.example.gagan.google.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.gagan.google.Manifest;
import com.example.gagan.google.R;
import com.example.gagan.google.fragments.BasePagerFragment;
import com.example.gagan.google.fragments.FirebaseDBFragment;
import com.example.gagan.google.fragments.GoogleMapFragment;
import com.example.gagan.google.fragments.GoogleSignIn;
import com.example.gagan.google.fragments.VoiceFragment;
import com.example.gagan.google.pojoclass.db.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gagan on 2/13/2018.
 */

public class Constant {

    public static final String Map_API_BASE_URL = "https://maps.googleapis.com/maps/api/";
    public static final String REF_USER = "users";
    public static final String REF_IMAGE = "images/user_pics/";
    public static final String PUSH_NOTIFICATION = "pushNotification";
    public static int NOTIFICATION_ID = 100;
    public static final int NOTIFICATION_ID_BIG_IMAGE = 101;
    public static User currentUser;
    public static final String MALE_PLACEHOLDER_URL = "https://firebasestorage.googleapis.com/v0/b/key-cistern-195108.appspot.com/o/images%2Fuser_pics%2Fmale_placeholder.jpg?alt=media&token=f9193ff0-b93e-4bb8-830c-d322159f696f";
    private final static String USER_KEY = "USER_KEY";
    private final static String MODE = "MODE";
/*
*   <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.RECORD_AUDIO" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.RECORD_AUDIO" />
*
* */
    public static String[] PERMISSIONS={
        android.Manifest.permission.RECORD_AUDIO,
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.ACCESS_NETWORK_STATE,
        android.Manifest.permission.INTERNET,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.RECORD_AUDIO
    };

    public static List<BasePagerFragment> getList() {
        List<BasePagerFragment> fragments = new ArrayList<>();
        fragments.add(0, new GoogleSignIn());
        fragments.add(1, new FirebaseDBFragment());
        fragments.add(2, new VoiceFragment());
        fragments.add(3, new GoogleMapFragment());
        fragments.add(fragments.size(), new BasePagerFragment());
        return fragments;
    }

    public static void Log(String tag, String message) {
        Log.d(tag, message);
    }

    public static void AddUniqueKey(Context context, User user) {
        SharedPreferences pref = context.getSharedPreferences(MODE, 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(USER_KEY, user.getId());
        editor.commit();
    }

    public static void RemoveUniqueKey(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MODE, 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(USER_KEY);
        editor.commit();
    }

    public static String getUserKey(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MODE, 0); // 0 - for private mode
        String key = pref.getString(USER_KEY, null); // getting String

        return key;
    }


    public static String getRecognizitionKeyword(Context applicationContext) {
        return "Hello";
    }
}
