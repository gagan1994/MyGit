package com.example.gagan.myexampleproject.fragments.home;

import android.content.Context;

import com.example.gagan.myexampleproject.fragments.home.HomeFragment;
import com.example.gagan.myexampleproject.uiadapters.RecyclerViewAdapter;
import com.squareup.picasso.Picasso;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Gagan on 3/1/2018.
 */
@Module
public class HomeFragmentModule {
    private final HomeFragment mFragment;

    public HomeFragmentModule(HomeFragment fragment) {
        this.mFragment = fragment;
    }

    @Provides
    @HomeFragmentScope
    public Context context(){
        return mFragment.getContext();
    }
    /*  @Provides
      @HomeFragmentScope
      public RecyclerViewAdapter recyclerViewAdapter(Picasso picasso){
          return new RecyclerViewAdapter(mFragment.getContext(),picasso);
      }*/
   /* @Provides
    @Named("fragment")
    public Context context() {
        return mFragment.getContext();
    }*/
}

