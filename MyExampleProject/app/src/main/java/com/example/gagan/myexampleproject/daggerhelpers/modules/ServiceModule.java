package com.example.gagan.myexampleproject.daggerhelpers.modules;

import com.example.gagan.myexampleproject.daggerhelpers.qualifiers.ApiInterfaceQualifer;
import com.example.gagan.myexampleproject.daggerhelpers.qualifiers.OpenConnectionInterfaceQualifier;
import com.example.gagan.myexampleproject.daggerhelpers.qualifiers.WeatherInterFaceQualifer;
import com.example.gagan.myexampleproject.daggerhelpers.scope.SingletonClassScope;
import com.example.gagan.myexampleproject.rest.ApiInterface;
import com.example.gagan.myexampleproject.rest.OpenConnectionInterface;
import com.example.gagan.myexampleproject.rest.WeatherInterFace;
import com.example.gagan.myexampleproject.utilhelper.Constant;
import com.fatboyindustrial.gsonjodatime.DateTimeConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.joda.time.DateTime;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Gagan on 3/1/2018.
 */
@Module(includes = NetworkModule.class)
public class ServiceModule {


    @Provides
    @SingletonClassScope
    public OpenConnectionInterface getOpenConnection(@OpenConnectionInterfaceQualifier Retrofit retrofit) {
        return retrofit.create(OpenConnectionInterface.class);
    }

    @Provides
    @SingletonClassScope
    @OpenConnectionInterfaceQualifier
    public Retrofit retrofit2(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Constant.BASE_URL_OPEN_CONNECTIONs)
                .build();
    }


    @Provides
    @SingletonClassScope
    public ApiInterface getApiInterface(@ApiInterfaceQualifer Retrofit retrofit) {
        return retrofit.create(ApiInterface.class);
    }

    @Provides
    @SingletonClassScope
    @ApiInterfaceQualifer
    public Retrofit retrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Constant.BASE_URL_API_INTERFACE)
                .build();
    }


    @Provides
    @SingletonClassScope
    public WeatherInterFace getWeatherInterface(@WeatherInterFaceQualifer Retrofit retrofit) {
        return retrofit.create(WeatherInterFace.class);
    }

    @Provides
    @SingletonClassScope
    @WeatherInterFaceQualifer
    public Retrofit retrofit1(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Constant.BASE_URL_WEATHER)
                .build();
    }

    @Provides
    @SingletonClassScope
    public Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeConverter());
        return gsonBuilder.create();
    }
}
