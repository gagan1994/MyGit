package com.example.gagan.designpatternexample.structural_design_pattern.adapter_pattern;

import com.example.gagan.designpatternexample.structural_design_pattern.AdapterPatternClass;

/**
 * Created by Gagan on 3/6/2018.
 */

public class MxPlayer implements MediaPackage {
    @Override
    public void playFile(String filename) {
        AdapterPatternClass.display("Playing: " + filename + "via Mx Player");
    }
}
