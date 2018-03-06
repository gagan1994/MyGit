package com.example.gagan.designpatternexample.structural_design_pattern.composite_pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gagan on 3/6/2018.
 */

public class Drawing extends Shape {
    private final List<Shape> shapes = new ArrayList<>();

    public void add(Shape shape) {
        shapes.add(shape);
    }

    public void remove(Shape shape) {
        shapes.remove(shape);
    }

    public void clear() {
        shapes.clear();
    }

    @Override
    public void draw(String color) {
        for (Shape s : shapes)
            s.draw(color);
    }
}
