package com.example.gagan.cloths.utils;

import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Gagan on 5/3/2018.
 */

public class Constants {
    private static Constants instance;

    private Constants() throws ClassNotFoundException {
        throw new ClassNotFoundException("User not Initilized");
    }

    private Constants(@NonNull FirebaseUser user) {
        this.USER = user;
    }

    public static Constants login(@NonNull FirebaseUser user) {
        instance = new Constants(user);
        return instance;
    }

    public static Constants getInstant() {
        return instance;
    }

    public FirebaseUser getUSER() {
        return USER;
    }


    public FirebaseUser USER;
}
