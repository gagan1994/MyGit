package com.example.gagan.designpatternexample.structural_design_pattern.adapter_pattern;

import com.example.gagan.designpatternexample.structural_design_pattern.AdapterPatternClass;

/**
 * Created by Gagan on 3/6/2018.
 */

public class FormatAdapter implements MediaPlayer {

    private final MediaPackage media;

    public FormatAdapter(MediaPackage m) {
        media = m;
    }

    @Override
    public void play(String file) {
        AdapterPatternClass.display("Using Adapter -->");
        media.playFile(file);
    }
}
