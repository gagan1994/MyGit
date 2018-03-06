package com.example.gagan.myexampleproject.fragments.home;

import com.example.gagan.myexampleproject.daggerhelpers.ApplicationComponent;
import com.example.gagan.myexampleproject.fragments.BasePagerFragment;
import com.example.gagan.myexampleproject.fragments.OpenConnectionFragment;
import com.example.gagan.myexampleproject.fragments.RxJavaPaginatorFragment;
import com.example.gagan.myexampleproject.rest.ApiInterface;
import com.example.gagan.myexampleproject.uiadapters.RecyclerViewAdapter;
import com.squareup.picasso.Picasso;

import dagger.Component;

/**
 * Created by Gagan on 3/1/2018.
 */
@BaseFragmentScope
@Component(modules = BaseFragmentModule.class, dependencies = ApplicationComponent.class)
public interface BaseFragmentComponent {
    void injectBaseFragment(BasePagerFragment fragment);

    void injectHomeFragment(HomeFragment fragment);

    void injectRxJavaPagerFragment(RxJavaPaginatorFragment fragment);

    void injectOpenConnectionFragment(OpenConnectionFragment fragment);

    /*RecyclerViewAdapter recyclerViewAdapter();

    Picasso getPicasso();

    ApiInterface getJsonRtrofit();*/
}
