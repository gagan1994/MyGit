package com.example.gagan.myexampleproject.rest;

import com.example.gagan.myexampleproject.pojoclass.UserClass;
import com.example.gagan.myexampleproject.pojoclass.Weather;
import com.example.gagan.myexampleproject.pojoclass.WeatherData;
import com.example.gagan.myexampleproject.utilhelper.Constant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Gagan on 3/1/2018.
 */

public interface ApiInterface {
    @GET(Constant.USER_URL)
    Call<List<UserClass>> getUsers(@Header("connection")String val);
    @GET(Constant.USER_URL)
    Call<List<UserClass>> getUsers();

    @GET(Constant.USER_URL)
    Observable<List<UserClass>> getUsersByObservable();


}
