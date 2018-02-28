package com.example.gagan.google.db;

import android.net.Uri;

import com.example.gagan.google.pojoclass.db.User;
import com.example.gagan.google.utils.Constant;
import com.example.gagan.google.utils.Utils;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

/**
 * Created by Gagan on 2/16/2018.
 */

public class DBUser {
    private static final DatabaseReference mDatabase = FirebaseDatabase
            .getInstance().getReference(Constant.REF_USER);
    private static final StorageReference imageReference = FirebaseStorage.getInstance().getReference().child(Constant.REF_IMAGE);
    ;

    public static void insertUser() {

    }

    public static void keepSynced(boolean b) {

        mDatabase.keepSynced(b);
    }

    public static Query getUserRef() {
        return mDatabase;
    }

    public static void AddUser(User user, OnSuccessListener listener) {
        String userId = mDatabase.push().getKey();
        user.setId(userId);
        if (user.getUri() == null) {
            user.setUri(Constant.MALE_PLACEHOLDER_URL);
        }
        Task<Void> uploadTask = mDatabase.child(userId).setValue(user);

        uploadTask.addOnSuccessListener(listener);
    }

    public static void AddUserWithImage(final User user, Uri uri, OnSuccessListener listener) {
        final String imageId = UUID.randomUUID().toString();
        StorageReference userImagesRef = imageReference.child(imageId);


        UploadTask uploadTask = userImagesRef.putFile(uri);

        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                user.setProfilePicId(imageId);
                user.setUri(taskSnapshot.getDownloadUrl().toString());
                AddUser(user, new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {

                    }
                });
            }
        });
    }

    public static void DeleteUser(User user, OnSuccessListener<Void> succesfullDeleteListner) {
        Task<Void> deleteTask = mDatabase.child(user.getId()).removeValue();
        deleteTask.addOnSuccessListener(succesfullDeleteListner);
    }

    public static void getUser(String key, ValueEventListener listner) {
        mDatabase.child(key).addListenerForSingleValueEvent(listner);
    }
}
