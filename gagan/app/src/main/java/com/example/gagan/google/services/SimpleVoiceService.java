package com.example.gagan.google.services;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;

import com.example.gagan.google.utils.Utils;

import java.util.ArrayList;


/**
 * Created by Gagan on 2/19/2018.
 */

public class SimpleVoiceService extends Service implements RecognitionListener {
    private SpeechRecognizer speechRecognizer;
    private static final String TAG = "SimpleVoiceService";

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate() {
        Utils.Toast(this, "My Service Created");
        Utils.Log("tag", "onCreate");
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(getApplicationContext());
        speechRecognizer.setRecognitionListener(this);

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);

        speechRecognizer.startListening(intent);
    }

    @Override
    public void onDestroy() {
        Utils.Toast(this, "My Service Stopped");
        Utils.Log("tag", "onDestroy");
    }

    @Override
    public void onStart(Intent intent, int startid) {
        Utils.Toast(this, "My Service Started");
        Utils.Log("tag", "onStart");
    }

    @Override
    public void onBeginningOfSpeech() {
        Utils.Log(TAG, "onBeginningOfSpeech");
    }

    @Override
    public void onBufferReceived(byte[] buffer) {
        Utils.Log(TAG, "onBufferReceived");
    }

    @Override
    public void onEndOfSpeech() {
        Utils.Log(TAG, "onEndOfSpeech");
    }

    @Override
    public void onError(int error) {
        Utils.Log(TAG, "onError");
    }

    @Override
    public void onEvent(int eventType, Bundle params) {
        Utils.Log(TAG, "onEvent");
    }

    @Override
    public void onPartialResults(Bundle partialResults) {
        Utils.Log(TAG, "onPartialResults");
    }

    @Override
    public void onReadyForSpeech(Bundle params) {
        Utils.Log(TAG, "onReadyForSpeech");
    }

    @Override
    public void onResults(Bundle results) {
        Utils.Log(TAG, "onResults");
        ArrayList strlist = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        for (int i = 0; i < strlist.size(); i++) {
            Utils.Log(TAG, "result=" + strlist.get(i));
        }
//        BufferedWriter out;
//        try {
//            out = new BufferedWriter(new FileWriter("mnt/sdcard/results.txt"));
////        out.write(processor.execute(strlist.get(0).toString()));
//            out.write("hello world");
//        } catch (IOException e) {
//            Utils.Log(TAG, e.toString());
//        }
    }

    @Override
    public void onRmsChanged(float rmsdB) {
        Utils.Log(TAG, "onRmsChanged");
    }

}