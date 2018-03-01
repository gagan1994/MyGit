package com.example.gagan.myexampleproject.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gagan.myexampleproject.R;

/**
 * Created by Gagan on 3/1/2018.
 */

public class BasePagerFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.help_layout, container, false);
        return view;
    }

    public String getTitle() {
        return "Help";
    }

    public String getCustomTag() {
        return "Tag";
    }
}
