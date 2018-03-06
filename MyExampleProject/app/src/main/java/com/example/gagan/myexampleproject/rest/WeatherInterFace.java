package com.example.gagan.myexampleproject.rest;

import com.example.gagan.myexampleproject.pojoclass.WeatherData;
import com.example.gagan.myexampleproject.utilhelper.Constant;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Gagan on 3/6/2018.
 */

public interface WeatherInterFace {
    @GET(Constant.API_URL)
    Observable<WeatherData> getWeather(@Query("q") String place, @Query("APPID") String APPID);

}
