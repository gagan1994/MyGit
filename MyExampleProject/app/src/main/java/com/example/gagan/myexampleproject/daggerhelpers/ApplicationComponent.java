package com.example.gagan.myexampleproject.daggerhelpers;

import android.content.Context;

import com.example.gagan.myexampleproject.daggerhelpers.modules.ContextModule;
import com.example.gagan.myexampleproject.daggerhelpers.modules.NetworkModule;
import com.example.gagan.myexampleproject.daggerhelpers.modules.PicassoModule;
import com.example.gagan.myexampleproject.daggerhelpers.modules.ServiceModule;
import com.example.gagan.myexampleproject.daggerhelpers.scope.SingletonClassScope;
import com.example.gagan.myexampleproject.rest.ApiInterface;
import com.example.gagan.myexampleproject.rest.OpenConnectionInterface;
 import com.example.gagan.myexampleproject.rest.WeatherInterFace;
import com.squareup.picasso.Picasso;

import dagger.Component;

/**
 * Created by Gagan on 3/1/2018.
 */
@SingletonClassScope
@Component(modules = {ServiceModule.class, PicassoModule.class})
public interface ApplicationComponent {

    Picasso getPicasso();

    ApiInterface getApiInterface();

    WeatherInterFace getWeatherInterFace();

    OpenConnectionInterface getOpenConnectionInterface();
}
