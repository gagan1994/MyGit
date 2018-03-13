
package com.example.gagan.myexampleproject.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.gagan.myexampleproject.MyApplication;
import com.example.gagan.myexampleproject.R;
import com.example.gagan.myexampleproject.fragments.BasePagerFragment;
import com.example.gagan.myexampleproject.fragments.home.BaseFragmentComponent;
import com.example.gagan.myexampleproject.fragments.home.BaseFragmentModule;
import com.example.gagan.myexampleproject.fragments.home.DaggerBaseFragmentComponent;
import com.example.gagan.myexampleproject.pojoclass.UserClass;
import com.example.gagan.myexampleproject.pojoclass.WeatherData;
import com.example.gagan.myexampleproject.rest.ApiInterface;
import com.example.gagan.myexampleproject.rest.WeatherInterFace;
import com.example.gagan.myexampleproject.uiadapters.RecyclerViewAdapter;
import com.example.gagan.myexampleproject.utilhelper.Constant;
import com.example.gagan.myexampleproject.utilhelper.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class RxJavaPaginatorFragment extends BasePagerFragment {

    public static final String TAG = "RxJavaPaginatorFragment";
    @BindView(R.id.recyclerView)
    RecyclerView rv_list;
    @BindView(R.id.activity_main_swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    BaseFragmentComponent baseFragmentComponent;
    @Inject
    Picasso picasso;
    @Inject
    WeatherInterFace apiWeather;

    @Inject
    ApiInterface apiInterface;
    @Inject
    RecyclerViewAdapter adapter;
    private LinearLayoutManager layoutManager;
    private boolean loading;
    private static final int VISIBLE_THRESHOLD = 3;
    private int pageNumber = 0;
    private String London;

    public RxJavaPaginatorFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rx_java_paginator, container, false);
        ButterKnife.bind(this, view);
        baseFragmentComponent = DaggerBaseFragmentComponent.builder()
                .baseFragmentModule(new BaseFragmentModule(this))
                .applicationComponent(MyApplication.applicationComponent())
                .build();
        baseFragmentComponent.injectRxJavaPagerFragment(this);
        layoutManager = new LinearLayoutManager(getContext());
        rv_list.setLayoutManager(layoutManager);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                callApi();
            }
        });
        swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        rv_list.setAdapter(adapter);
        addListners();
        callApi();
        getWeather();
        return view;
    }

    private void getWeather() {
        London = "London";
        apiWeather.getWeather(London, Constant.ApiId).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeatherData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(WeatherData weatherData) {
                        Log.e("Current Weather", weatherData.getWeather().get(0)
                                .getDescription());
                        Utils.Toast(getActivity(), weatherData.getWeather().get(0)
                                .getDescription());
                    }
                });
        ;
    }

    private void callApi() {
        loadMore();
    }

    private void addListners() {
        rv_list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView,
                                   int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int totalItemCount = layoutManager.getItemCount();
                int lastVisibleItem = layoutManager
                        .findLastVisibleItemPosition();
                if (!loading
                        && totalItemCount <= (lastVisibleItem + VISIBLE_THRESHOLD)) {
                    pageNumber++;
                    loadMore();
                    loading = true;
                }
            }
        });
    }

    private void loadMore() {

        apiInterface.getUsersByObservable().
                subscribeOn(Schedulers.io())
                .delay(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<UserClass>>() {
                    @Override
                    public void onCompleted() {
                        loading = false;
                        swipeRefreshLayout.setRefreshing(false);

                    }

                    @Override
                    public void onError(Throwable e) {
                        swipeRefreshLayout.setRefreshing(false);

                    }

                    @Override
                    public void onNext(List<UserClass> userClasses) {
                        adapter.addAll(userClasses);
                    }
                });
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
/*

* */