package com.example.gagan.myexampleproject;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.example.gagan.myexampleproject.daggerhelpers.ApplicationComponent;
import com.example.gagan.myexampleproject.daggerhelpers.DaggerApplicationComponent;
import com.example.gagan.myexampleproject.daggerhelpers.modules.ContextModule;
import com.example.gagan.myexampleproject.daggerhelpers.modules.NetworkModule;
import com.example.gagan.myexampleproject.daggerhelpers.modules.PicassoModule;
import com.example.gagan.myexampleproject.daggerhelpers.modules.ServiceModule;
import com.example.gagan.myexampleproject.rest.ApiInterface;
import com.example.gagan.myexampleproject.utilhelper.Constant;
import com.fatboyindustrial.gsonjodatime.DateTimeConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by Gagan on 3/1/2018.
 */

public class MyApplication extends Application {
    private static ApplicationComponent applicationComponent;
/*
*           Activity
*    jsonRetrofit         picasso
*  Gson                 okhttp
*                   logger       cache
 *                  timber      file
*
* */

    @Override
    public void onCreate() {
        super.onCreate();
        //Context
        Timber.plant(new Timber.DebugTree());
        applicationComponent = DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .networkModule(new NetworkModule())
                .picassoModule(new PicassoModule())
                .serviceModule(new ServiceModule())
                .build();
    }


    public static ApplicationComponent applicationComponent() {
        return applicationComponent;
    }

    public static MyApplication getApplication(Activity activity) {
        return (MyApplication) activity.getApplication();
    }
}
    /* //Network
       /* HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.i(message);
            }
        });
        File cache = new File(getCacheDir(), "okhttp_cache");
        cache.mkdir();
        Cache cachel = new Cache(cache, 10 * 1000 * 1000);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cachel)
                .addInterceptor(loggingInterceptor)
                .build();*/
//Picasso
       /* picasso = new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(okHttpClient))
                .build();*/

//Client
        /*GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeConverter());
        Gson gson = gsonBuilder.create();
*/
       /* Retrofit myJsonRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(Constant.BASE_URL)
                .build();*/
//     jsonRtrofit = myJsonRetrofit.create(ApiInterface.class);*/

