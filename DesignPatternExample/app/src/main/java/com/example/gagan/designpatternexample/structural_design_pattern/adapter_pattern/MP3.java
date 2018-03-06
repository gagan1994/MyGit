package com.example.gagan.designpatternexample.structural_design_pattern.adapter_pattern;

import com.example.gagan.designpatternexample.structural_design_pattern.AdapterPatternClass;

/**
 * Created by Gagan on 3/6/2018.
 */

public class MP3 implements MediaPlayer {
    @Override
    public void play(String filename) {
        AdapterPatternClass.display("Playing MP3 File " + filename);
    }
}
