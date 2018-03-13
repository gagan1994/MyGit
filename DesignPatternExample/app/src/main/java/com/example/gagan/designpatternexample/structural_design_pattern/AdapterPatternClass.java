package com.example.gagan.designpatternexample.structural_design_pattern;

import android.content.Context;
import android.util.Log;

import com.example.gagan.designpatternexample.helpers.BaseHelperClass;
import com.example.gagan.designpatternexample.structural_design_pattern.adapter_pattern.FormatAdapter;
import com.example.gagan.designpatternexample.structural_design_pattern.adapter_pattern.MP3;
import com.example.gagan.designpatternexample.structural_design_pattern.adapter_pattern.MediaPlayer;
import com.example.gagan.designpatternexample.structural_design_pattern.adapter_pattern.MxPlayer;
import com.example.gagan.designpatternexample.structural_design_pattern.adapter_pattern.Vlc;

/**
 * Created by Gagan on 3/6/2018.
 */

public class AdapterPatternClass extends BaseHelperClass {
    @Override
    public void execute(Context context) {
        MediaPlayer player = new MP3();
        player.play("file.mp3");
        player = new FormatAdapter(new Vlc());
        player.play("file.mp4");
        player = new FormatAdapter(new MxPlayer());
        player.play("file.avi");

    }

    @Override
    public String getUrl() {
        return "https://cdn.journaldev.com/wp-content/uploads/2013/07/adapter-pattern-java-class-diagram.png";
    }

    public static void display(String s) {
        Log.w("AdapterPatternClass", s);
    }
}
