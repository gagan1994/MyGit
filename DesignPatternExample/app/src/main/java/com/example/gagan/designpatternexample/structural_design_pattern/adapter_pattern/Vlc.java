package com.example.gagan.designpatternexample.structural_design_pattern.adapter_pattern;

import android.util.Log;

import com.example.gagan.designpatternexample.structural_design_pattern.AdapterPatternClass;

/**
 * Created by Gagan on 3/6/2018.
 */

public class Vlc implements MediaPackage {
    @Override
    public void playFile(String filename) {
        AdapterPatternClass.display("Playing :" + filename + " via Vlc player");
    }
}
