package com.example.gagan.myexampleproject.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gagan.myexampleproject.MyApplication;
import com.example.gagan.myexampleproject.R;
import com.example.gagan.myexampleproject.fragments.home.BaseFragmentComponent;
import com.example.gagan.myexampleproject.fragments.home.BaseFragmentModule;
import com.example.gagan.myexampleproject.fragments.home.DaggerBaseFragmentComponent;
import com.example.gagan.myexampleproject.pojoclass.UserClass;
import com.example.gagan.myexampleproject.rest.ApiInterface;
import com.example.gagan.myexampleproject.rest.OpenConnectionInterface;
import com.example.gagan.myexampleproject.uiadapters.RecyclerViewAdapter;
import com.example.gagan.myexampleproject.utilhelper.Constant;
import com.example.gagan.myexampleproject.utilhelper.Utils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class OpenConnectionFragment extends BasePagerFragment {
    public static final String TAG = "OpenConnectionFragment";
    private BaseFragmentComponent baseFragmentComponent;
    @Inject
    RecyclerViewAdapter adapter;
    @Inject
    OpenConnectionInterface apiOpenConnectionInterface;
    private LinearLayoutManager layoutManager;

    @BindView(R.id.rv_list)
    RecyclerView rv_list;

    @Override
    public String getTitle() {
        return "Opened Connection";
    }

    @Override
    public String getCustomTag() {
        return TAG;
    }

    public OpenConnectionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_open_connection, container, false);
        ButterKnife.bind(this, view);
        baseFragmentComponent = DaggerBaseFragmentComponent.builder()
                .baseFragmentModule(new BaseFragmentModule(this))
                .applicationComponent(MyApplication.applicationComponent())
                .build();
        baseFragmentComponent.injectOpenConnectionFragment(this);

        layoutManager = new LinearLayoutManager(getContext());
        rv_list.setLayoutManager(layoutManager);
        rv_list.setAdapter(adapter);
        // Inflate the layout for this fragment

        callApi();
        return view;
    }

    private void callApi() {
        apiOpenConnectionInterface.getOpenConnection(Constant.getHEADER()).enqueue(new Callback<List<UserClass>>() {
            @Override
            public void onResponse(Call<List<UserClass>> call, Response<List<UserClass>> response) {
                String kee=call.request().header("connection");
                Log.w("asd",kee+" ");
                Utils.   Toast(getActivity(), response.isSuccessful() ? "Succesfull" : "UnSuccesfull");
                if (response.isSuccessful())
                    adapter.resetData(new ArrayList<>(response.body()));
            }

            @Override
            public void onFailure(Call<List<UserClass>> call, Throwable t) {

            }
        });
    }

}
