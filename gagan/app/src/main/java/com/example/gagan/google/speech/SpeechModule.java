package com.example.gagan.google.speech;

import android.content.Context;

import com.example.gagan.google.utils.Utils;

import edu.cmu.pocketsphinx.Hypothesis;
import edu.cmu.pocketsphinx.RecognitionListener;

/**
 * Created by Gagan on 2/19/2018.
 */

public class SpeechModule implements RecognitionListener {
    private final Context mContext;
    private static final String TAG = "SpeechModule";

    public SpeechModule(Context context) {
        mContext = context;
    }

    @Override
    public void onBeginningOfSpeech() {
        Utils.Log(TAG,"onBeginningOfSpeech");
    }

    @Override
    public void onEndOfSpeech() {
        Utils.Log(TAG,"onEndOfSpeech");

    }

    @Override
    public void onPartialResult(Hypothesis hypothesis) {
        Utils.Log(TAG,"onPartialResult");

    }

    @Override
    public void onResult(Hypothesis hypothesis) {
        Utils.Log(TAG,"onResult");

    }

    @Override
    public void onError(Exception e) {
        Utils.Log(TAG,"onError");

    }

    @Override
    public void onTimeout() {
        Utils.Log(TAG,"");

    }
}
