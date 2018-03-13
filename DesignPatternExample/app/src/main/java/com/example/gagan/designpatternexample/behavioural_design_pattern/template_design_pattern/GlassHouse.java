package com.example.gagan.designpatternexample.behavioural_design_pattern.template_design_pattern;

import com.example.gagan.designpatternexample.behavioural_design_pattern.TemplateDesignPattern;

/**
 * Created by Gagan on 3/13/2018.
 */

public class GlassHouse extends HouseTemplate {
    @Override
    public void buildWalls() {
        TemplateDesignPattern.display("Building Glass Walls");
    }

    @Override
    public void buildPillars() {
        TemplateDesignPattern.display("Building Pillars with glass coating");
    }
}
