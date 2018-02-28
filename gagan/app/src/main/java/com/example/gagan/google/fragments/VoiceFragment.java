package com.example.gagan.google.fragments;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.gagan.google.R;
import com.example.gagan.google.services.SimpleVoiceService;
import com.example.gagan.google.utils.Utils;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

import static android.icu.text.DateTimePatternGenerator.PatternInfo.OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class VoiceFragment extends BasePagerFragment {
    public static final String TAG = "VoiceFragment";

    @BindDrawable(R.drawable.ic_action_recorder)
    Drawable recording;
    @BindDrawable(R.drawable.ic_action_recording)
    Drawable recorder;
    @BindView(R.id.fab_record)
    FloatingActionButton fab_record;

    public VoiceFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_voice, container, false);
        ButterKnife.bind(this, view);
        fab_record.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startRecording();
                Utils.Log(TAG, "onLongClick");
                fab_record.setImageDrawable(recording);

                return false;
            }
        });
        fab_record.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        Utils.Log(TAG, "ACTION_UP");
                        fab_record.setImageDrawable(recorder);
                        getResult();
                        break;
                }
                return false;
            }
        });

        return view;
    }

    private void startRecording() {
        getActivity().startService(new Intent(getActivity(), SimpleVoiceService.class));
    }

    private void getResult() {
        getActivity().stopService(new Intent(getActivity(), SimpleVoiceService.class));

    }

    private void stopRecording() {
        getActivity().stopService(new Intent(getActivity(), SimpleVoiceService.class));
    }

    @Override
    public String getTitle() {
        return "Voice Recording";
    }

    @Override
    public String getCustomTag() {
        return TAG;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!=OK)
            return;
        switch (requestCode){

        }
    }
}
