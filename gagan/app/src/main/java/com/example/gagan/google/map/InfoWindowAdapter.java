package com.example.gagan.google.map;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.gagan.google.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by Gagan on 2/14/2018.
 */

public class InfoWindowAdapter implements GoogleMap.InfoWindowAdapter {


    private String place;

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPlace() {
        return place;
    }

    public interface OnClickMarkerLayout {
        public void OnClickMarkerLayout(Marker marker);
    }

    OnClickMarkerLayout listner;
    private final Context mContext;

    public InfoWindowAdapter(Context context, @NonNull OnClickMarkerLayout listner) {
        this.mContext = context;
        this.listner = listner;
    }

    @Override
    public View getInfoWindow(Marker marker) {

        return null;
    }

    @Override
    public View getInfoContents(final Marker marker) {
        View infoWindow = LayoutInflater.from(mContext).inflate(R.layout.pointer_info, null);
        TextView title = ((TextView) infoWindow.findViewById(R.id.title));
        title.setText(marker.getTitle());
        TextView snippet = ((TextView) infoWindow.findViewById(R.id.snippet));
        snippet.setText(marker.getSnippet());
        infoWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listner.OnClickMarkerLayout(marker);
            }
        });
        return infoWindow;
    }
}
