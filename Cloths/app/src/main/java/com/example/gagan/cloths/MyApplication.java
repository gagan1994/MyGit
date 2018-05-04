package com.example.gagan.cloths;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Gagan on 5/4/2018.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fabric fabric = new Fabric.Builder(this)
                .kits(new Crashlytics())
                .debuggable(true)
                .build();
        Fabric.with(fabric);

//        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setApiKey("AI...j0")
//                .setApplicationId("1:5...e0")
//                .setDatabaseUrl("https://myapp.firebaseio.com")
//                .build();
//        FirebaseApp secondApp = FirebaseApp.initializeApp(getApplicationContext(), options, "second app");

    }
}
