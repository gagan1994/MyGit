package com.example.gagan.proj1.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gagan.proj1.MainActivity;
import com.example.gagan.proj1.R;
import com.example.gagan.proj1.adapter.FirebaseUserListAdapter;
import com.example.gagan.proj1.adapter.UsersListViewHolder;
import com.example.gagan.proj1.dbhelper.DbHelper;
import com.example.gagan.proj1.interfaces.UpdateUserInterface;
import com.example.gagan.proj1.pojo.User;
import com.example.gagan.proj1.widgets.SeparatorDecoration;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.ObservableSnapshotArray;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class LogInFragment extends BaseFragment implements UpdateUserInterface {
    public final static int Id = 1;
    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;

    private FirebaseRecyclerAdapter<User, UsersListViewHolder> mFirebaseAdapter;


    public LogInFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log_in, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        final DatabaseReference usersRef = DbHelper.getDbHepler().getUsersRegForThisRef();
        Query query = usersRef
                .limitToLast(50);
        FirebaseRecyclerOptions<User> options =
                new FirebaseRecyclerOptions.Builder<User>()
                        .setQuery(query, User.class)
                        .build();
        mFirebaseAdapter = new FirebaseUserListAdapter(options, this);
        mRecyclerView.addItemDecoration(new SeparatorDecoration(getActivity(), Color.TRANSPARENT, 5));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mFirebaseAdapter);
        mFirebaseAdapter.startListening();
        if (mFirebaseAdapter.getItemCount() == 0) {

        } else {

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mFirebaseAdapter.stopListening();
    }

    @Override
    public String getTitle() {
        return "Home";
    }

    @Override
    public void OnClickUser(User user,View view) {
        ((MainActivity) getActivity()).openChatt(user);
    }
}
