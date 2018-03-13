package com.example.gagan.designpatternexample.structural_design_pattern;

import android.content.Context;
import android.util.Log;

import com.example.gagan.designpatternexample.helpers.BaseHelperClass;
import com.example.gagan.designpatternexample.structural_design_pattern.composite_pattern.Circle;
import com.example.gagan.designpatternexample.structural_design_pattern.composite_pattern.Drawing;
import com.example.gagan.designpatternexample.structural_design_pattern.composite_pattern.Triangle;

/**
 * Created by Gagan on 3/6/2018.
 */

public class CompositeDesignPattern extends BaseHelperClass {
    @Override
    public void execute(Context context) {
        Drawing drawing = new Drawing();
        Triangle triangle = new Triangle();
        Circle circle = new Circle();

        drawing.add(triangle);
        drawing.add(circle);
        drawing.draw("RED");
        drawing.clear();
        drawing.add(circle);
        drawing.draw("BLUE");
    }

    @Override
    public String getUrl() {
        return "https://cdn.journaldev.com/wp-content/uploads/2013/07/Composite-Pattern-java.png";
    }

    public static void display(String s) {
        Log.w("CompositePattern", s);
    }
}
