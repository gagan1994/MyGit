package com.example.gagan.google.rest;


import com.example.gagan.google.pojoclass.mappojo.MapResponse;
import com.google.android.gms.maps.model.LatLng;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Gagan on 2/14/2018.
 */

public interface RestApi {
    @GET("geocode/json")
    Call<MapResponse> getPlaces(@Query("latlng") String latlong);

    @GET("place/details/json")
    Call<MapResponse> getPlaceDetail(@Query("placeid") String placeId);
}
