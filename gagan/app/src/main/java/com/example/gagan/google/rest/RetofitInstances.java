package com.example.gagan.google.rest;

import com.example.gagan.google.utils.Constant;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Gagan on 2/14/2018.
 */

public class RetofitInstances {

    public static Retrofit getMapInstance() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(Constant.Map_API_BASE_URL)
                        .addConverterFactory(
                                GsonConverterFactory.create()
                        );
        Retrofit retrofit = builder
                .client(httpClient.build())
                .build();
        return retrofit;
    }
}
