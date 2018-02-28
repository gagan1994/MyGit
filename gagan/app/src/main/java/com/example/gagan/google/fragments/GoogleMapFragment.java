package com.example.gagan.google.fragments;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gagan.google.R;
import com.example.gagan.google.map.GPSTracker;

import com.example.gagan.google.pojoclass.mappojo.MapPojo;
import com.example.gagan.google.pojoclass.mappojo.MapResponse;
import com.example.gagan.google.rest.RestApi;
import com.example.gagan.google.rest.RetofitInstances;
import com.example.gagan.google.utils.Constant;
import com.example.gagan.google.utils.Utils;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.location.places.PlacePhotoMetadataResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.PlacesOptions;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoogleMapFragment extends BasePagerFragment implements GPSTracker.OnUpdateLocation,
        GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener,
        GoogleMap.OnMapClickListener, GoogleMap.OnMarkerClickListener,
        GoogleMap.OnInfoWindowClickListener {
    @BindView(R.id.mapView)
    MapView mMapView;
    @BindView(R.id.fab_satellite)
    FloatingActionButton fab_satellite;


    public static final String TAG = "GoogleMapFragment";
    private GoogleMap googleMap;
    private GPSTracker mGPS;
    @BindDrawable(R.drawable.ic_action_sattelite_selected)
    Drawable selected;
    @BindDrawable(R.drawable.ic_action_settlite)
    Drawable unSelected;
    private boolean isSelected = false;
    private String placeId;

    public GoogleMapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_google_map, container, false);
        ButterKnife.bind(this, view);
        mMapView.onCreate(savedInstanceState);
        initMap();
        intLocationListner();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }


    private void initMap() {
        mMapView.onResume(); // needed to get the map to display immediately
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
            googleMap.setOnMyLocationButtonClickListener(this);
            googleMap.setOnMyLocationClickListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
                if (ActivityCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }

                googleMap.setMyLocationEnabled(true);

                // For dropping a marker at a point on the Map
              /*  LatLng sydney = new LatLng(-34, 151);
                googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description"));
*/
                // For zooming automatically to the location of the marker
                /*CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));*/
                googleMap.setOnMapClickListener(GoogleMapFragment.this);
                googleMap.setOnMarkerClickListener(GoogleMapFragment.this);
                googleMap.setOnInfoWindowClickListener(GoogleMapFragment.this);

            }
        });
    }


    @OnClick(R.id.fab_satellite)
    public void onClick() {
        toggle();
        if (isSelected) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        } else {
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
    }


    private void toggle() {
        isSelected = !isSelected;
        fab_satellite.setImageDrawable(isSelected ? selected : unSelected);
    }

    private void intLocationListner() {
        mGPS = new GPSTracker(getActivity(), this);
        if (mGPS.canGetLocation()) {
            Location location = mGPS.getLocation();
            /*Update(location, camera);*/
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            if (ActivityCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            if (googleMap != null) {
                googleMap.setMyLocationEnabled(true);
                googleMap.getUiSettings().setMyLocationButtonEnabled(true);
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 20);
                googleMap.animateCamera(cameraUpdate);
            }
        }
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        // https://maps.googleapis.com/maps/api/geocode/json?latlng=lat,lng&key=YOUR_API_KEY
        RestApi api = RetofitInstances.getMapInstance().create(RestApi.class);
        placeId = null;
        Call<MapResponse> call = api.getPlaces(marker.getPosition().latitude + "," + marker.getPosition().longitude);
        call.enqueue(new Callback<MapResponse>() {
            @Override
            public void onResponse(Call<MapResponse> call, Response<MapResponse> response) {
                if (response.isSuccessful() && response.body().getResults().size() > 0) {

                    MapPojo firstAddress = response.body().getResults().get(0);
                    marker.setTitle(firstAddress.getAddress());
                    String addresType = "";
                    if (firstAddress.getTypes().size() == 1) {
                        addresType = firstAddress.getTypes().get(0);
                    } else {
                        for (String s : firstAddress.getTypes()) {
                            addresType = addresType + s + ",";
                        }
                    }
                    placeId = firstAddress.getPlaceId();
                    marker.setSnippet(addresType);
                    marker.showInfoWindow();
                }
            }

            @Override
            public void onFailure(Call<MapResponse> call, Throwable t) {

            }
        });
        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Utils.Toast(getActivity(), marker.getTitle());
    }

    @Override
    public void onMapClick(LatLng latLng) {
        Constant.Log(TAG, "onMapClick");
        // For dropping a marker at a point on the Map
        googleMap.clear();
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        try {
            List<Address> addressList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            for (Address address : addressList) {
                String url = address.getUrl();

                googleMap.addMarker(new MarkerOptions().position(latLng));
                CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng)
                        .zoom(20).build();

                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getSnippet(Address address) {

        return address.getSubLocality() + "," + address.getLocality() + "," + address.getAdminArea() + "," + address.getCountryName();
    }

    private String getTitle(Address address) {
        String ret = address.getPremises() + ","
                + address.getThoroughfare() + ","
                + address.getSubLocality() + ","
                + address.getPostalCode();
        return ret;
    }

    @Override
    public void Update(Location location) {
        Constant.Log(TAG, "onLocationChanged");
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Constant.Log(TAG, "onMyLocationButtonClick");
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Constant.Log(TAG, "onMyLocationClick");

    }


    @Override
    public String getTitle() {
        return "Map";
    }

    @Override
    public String getCustomTag() {
        return TAG;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }


}

