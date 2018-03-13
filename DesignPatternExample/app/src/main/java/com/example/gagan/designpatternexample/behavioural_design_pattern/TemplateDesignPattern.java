package com.example.gagan.designpatternexample.behavioural_design_pattern;

import android.content.Context;
import android.util.Log;

import com.example.gagan.designpatternexample.behavioural_design_pattern.template_design_pattern.GlassHouse;
import com.example.gagan.designpatternexample.behavioural_design_pattern.template_design_pattern.HouseTemplate;
import com.example.gagan.designpatternexample.behavioural_design_pattern.template_design_pattern.WoodenHouse;
import com.example.gagan.designpatternexample.helpers.BaseHelperClass;

/**
 * Created by Gagan on 3/13/2018.
 */

public class TemplateDesignPattern extends BaseHelperClass {
    @Override
    public void execute(Context context) {
        HouseTemplate houseType = new WoodenHouse();

        houseType.buildHouse();
        System.out.println("************");

        houseType = new GlassHouse();

        houseType.buildHouse();
    }

    @Override
    public String getUrl() {
        return "https://cdn.journaldev.com/wp-content/uploads/2013/07/template-method-pattern.png";
    }

    public static void display(String mess) {
        Log.w("TemplatePattern", mess);
    }
}
