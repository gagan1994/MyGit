package com.example.gagan.myexampleproject.rest;

import com.example.gagan.myexampleproject.pojoclass.UserClass;
import com.example.gagan.myexampleproject.utilhelper.Constant;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;

/**
 * Created by Gagan on 3/6/2018.
 */

public interface OpenConnectionInterface {

    //OpenConnection
    @GET(Constant.USER_URL)
    Call<List<UserClass>> getOpenConnection(@HeaderMap Map<String,String> headers);
}
