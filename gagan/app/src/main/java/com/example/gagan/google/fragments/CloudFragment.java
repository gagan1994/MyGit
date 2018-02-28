package com.example.gagan.google.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gagan.google.R;
import com.google.firebase.iid.FirebaseInstanceId;

import butterknife.ButterKnife;


public class CloudFragment extends BasePagerFragment {
    public static final String TAG = "";


    public CloudFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cloud, container, false);
        ButterKnife.bind(this, view);
        String token = FirebaseInstanceId.getInstance().getToken();
        token=token;
        return view;
    }

    @Override
    public String getTitle() {
        return "Firebase cloud Messaging";
    }

    @Override
    public String getCustomTag() {
        return super.getCustomTag();
    }

}
