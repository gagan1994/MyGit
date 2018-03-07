package com.example.gagan.myexampleproject.fragments.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gagan.myexampleproject.MyApplication;
import com.example.gagan.myexampleproject.R;
import com.example.gagan.myexampleproject.daggerhelpers.DaggerApplicationComponent;
import com.example.gagan.myexampleproject.fragments.BasePagerFragment;
import com.example.gagan.myexampleproject.interfaces.uiinterface.HomeFragmentRecyclerModel;
import com.example.gagan.myexampleproject.interfaces.uiinterface.RecyclerViewAdapter_Callback;
import com.example.gagan.myexampleproject.pojoclass.UserClass;
import com.example.gagan.myexampleproject.rest.ApiInterface;
import com.example.gagan.myexampleproject.uiadapters.RecyclerViewAdapter;
import com.example.gagan.myexampleproject.utilhelper.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedList;
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
public class HomeFragment extends BasePagerFragment {
    @BindView(R.id.rv_view)
    RecyclerView rv_view;


    private static final String TAG = "HomeFragment";
    private BaseFragmentComponent homeFragmentComponent;

    @Inject
    RecyclerViewAdapter adapter;
    @Inject
    ApiInterface apiInterface;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        homeFragmentComponent = DaggerBaseFragmentComponent.builder()
                .applicationComponent(MyApplication.applicationComponent())
                .baseFragmentModule(new BaseFragmentModule(this))
                .build();

        //  adapter = homeFragmentComponent.recyclerViewAdapter();
        homeFragmentComponent.injectHomeFragment(this);
        rv_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_view.setAdapter(adapter);
        callApi();
        callDummy();
        return view;
    }

    private void callDummy() {
        Call<List<UserClass>> call = apiInterface.getUsers("close");
        call.enqueue(new Callback<List<UserClass>>() {
            @Override
            public void onResponse(Call<List<UserClass>> call, Response<List<UserClass>> response) {
                String kee=call.request().header("connection");

                 if (response.isSuccessful()){
                    adapter.resetData(new ArrayList<HomeFragmentRecyclerModel>(response.body()));
                    Utils.Toast(getContext(), "succesfully loaded");
                }

            }

            @Override
            public void onFailure(Call<List<UserClass>> call, Throwable t) {
                Utils.Toast(getContext(), "error");
            }
        });
    }

    private void callApi() {
        Call<List<UserClass>> call = apiInterface.getUsers();
        call.enqueue(new Callback<List<UserClass>>() {
            @Override
            public void onResponse(Call<List<UserClass>> call, Response<List<UserClass>> response) {
                String kee=call.request().header("connection");
                if (response.isSuccessful()){
                    adapter.resetData(new ArrayList<HomeFragmentRecyclerModel>(response.body()));
                    Utils.Toast(getContext(), "succesfully loaded");
                }

            }

            @Override
            public void onFailure(Call<List<UserClass>> call, Throwable t) {
                Utils.Toast(getContext(), "error");
            }
        });

    }

    @Override
    public String getTitle() {
        return "Example";
    }

    @Override
    public String getCustomTag() {
        return TAG;
    }

}
