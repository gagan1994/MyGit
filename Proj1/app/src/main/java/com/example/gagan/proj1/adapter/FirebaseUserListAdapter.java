package com.example.gagan.proj1.adapter;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gagan.proj1.R;
import com.example.gagan.proj1.interfaces.UpdateUserInterface;
import com.example.gagan.proj1.pojo.User;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseError;

/**
 * Created by Gagan on 4/18/2018.
 */

public class FirebaseUserListAdapter extends FirebaseRecyclerAdapter<User, UsersListViewHolder> {

    private UpdateUserInterface listener;
    public FirebaseUserListAdapter(@NonNull FirebaseRecyclerOptions<User> options,UpdateUserInterface listener) {
        super(options);
        this.listener=listener;
    }

    @Override
    public void onError(@NonNull DatabaseError error) {
        super.onError(error);
        Log.w("Error", error.getMessage());
    }

    @Override
    protected void onBindViewHolder(@NonNull UsersListViewHolder holder, int position, @NonNull User model) {
        holder.bindUser(model,listener);
    }

    @NonNull
    @Override
    public UsersListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_user_lists, null, false);
        return new UsersListViewHolder(view);
    }
}
