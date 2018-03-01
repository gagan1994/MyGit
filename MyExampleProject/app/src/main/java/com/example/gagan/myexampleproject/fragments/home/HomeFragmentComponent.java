package com.example.gagan.myexampleproject.fragments.home;

import com.example.gagan.myexampleproject.daggerhelpers.ApplicationComponent;
import com.example.gagan.myexampleproject.rest.ApiInterface;
import com.example.gagan.myexampleproject.uiadapters.RecyclerViewAdapter;
import com.squareup.picasso.Picasso;

import dagger.Component;

/**
 * Created by Gagan on 3/1/2018.
 */
@HomeFragmentScope
@Component(modules = HomeFragmentModule.class, dependencies = ApplicationComponent.class)
public interface HomeFragmentComponent {
    void injectHomeFragment(HomeFragment fragment);
    /*RecyclerViewAdapter recyclerViewAdapter();

    Picasso getPicasso();

    ApiInterface getJsonRtrofit();*/
}
