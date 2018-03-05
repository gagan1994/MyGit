package com.example.gagan.designpatternexample.creational_design_patterns;

import android.content.Context;
import android.util.Log;

import com.example.gagan.designpatternexample.creational_design_patterns.builderpattern.Computer;
import com.example.gagan.designpatternexample.helpers.BaseHelperClass;

/**
 * Created by Gagan on 3/5/2018.
 */

public class BuilderPatternClasss extends BaseHelperClass {

    @Override
    public void execute(Context context) {
        Computer pc = new Computer.ComputerBuilder("2 TB", "120 GHZ")
                .isBluetoothEnabled(false)
                .isGraphicsCardEnabled(true)
                .build();
        Log.w("", "Computer :" + pc.toString());
    }
}
