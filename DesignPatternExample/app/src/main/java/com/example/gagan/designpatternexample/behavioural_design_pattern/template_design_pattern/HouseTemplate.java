package com.example.gagan.designpatternexample.behavioural_design_pattern.template_design_pattern;

import com.example.gagan.designpatternexample.behavioural_design_pattern.TemplateDesignPattern;

/**
 * Created by Gagan on 3/13/2018.
 */

public abstract class HouseTemplate {
    public HouseTemplate() {
    }

    public final void buildHouse() {
        buildFoundation();
        buildPillars();
        buildWalls();
        buildWindows();
        TemplateDesignPattern.display("House is built.");
    }

    private void buildWindows() {
        TemplateDesignPattern.display("Building Glass Windows");
    }

    public abstract void buildWalls();

    public abstract void buildPillars();

    private void buildFoundation() {
        TemplateDesignPattern.display("Building foundation with cement,iron rods and sand");
    }
}