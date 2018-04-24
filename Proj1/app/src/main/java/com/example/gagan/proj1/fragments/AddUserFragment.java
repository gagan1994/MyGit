package com.example.gagan.proj1.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gagan.proj1.R;
import com.example.gagan.proj1.adapter.UsersListAdapter;
import com.example.gagan.proj1.dbhelper.DbHelper;
import com.example.gagan.proj1.interfaces.UpdateUserInterface;
import com.example.gagan.proj1.pojo.User;
import com.example.gagan.proj1.pojo.UsersId;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddUserFragment extends BaseFragment implements UpdateUserInterface {
    @BindView(R.id.list_users)
    RecyclerView list_users;
    @BindView(R.id.et_search)
    AppCompatEditText et_search;
    List<User> usersList = new ArrayList<>();
    private UsersListAdapter adapter;

    public AddUserFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_user, container, false);
        ButterKnife.bind(this, view);
        initUsersList();
        return view;
    }

    private void initUsersList() {
        DatabaseReference users = DbHelper.getDbHepler().getUsersRef();

    }

    private void initRecycler() {
        if (adapter == null) {
            adapter = new UsersListAdapter(usersList, this);
            list_users.setLayoutManager(new LinearLayoutManager(getActivity()));
            list_users.setAdapter(adapter);
        }


    }
    @Override
    public void OnClickUser(User user, View view) {
        DbHelper.getDbHepler().addOrRemoveUserToCurrentUserList(user);
    }
}
