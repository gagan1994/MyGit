
package com.example.gagan.myexampleproject.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gagan.myexampleproject.R;
import com.example.gagan.myexampleproject.fragments.BasePagerFragment;
import com.example.gagan.myexampleproject.fragments.home.BaseFragmentComponent;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RxJavaPaginatorFragment extends BasePagerFragment {

    public static final String TAG = "RxJavaPaginatorFragment";
    @BindView(R.id.rv_list)
    RecyclerView rv_list;
    BaseFragmentComponent baseFragmentComponent;
    public RxJavaPaginatorFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rx_java_paginator, container, false);
        ButterKnife.bind(view);
        baseFragmentComponent.injectBaseFragment(this);
        return view;
    }

    @Override
    public String getTitle() {
        return "Rx java Paginator";
    }

    @Override
    public String getCustomTag() {
        return TAG;
    }

}
