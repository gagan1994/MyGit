package com.example.gagan.designpatternexample.structural_design_pattern;

import android.content.Context;

import com.example.gagan.designpatternexample.helpers.BaseHelperClass;
import com.example.gagan.designpatternexample.structural_design_pattern.decorator_pattren.BasicCar;
import com.example.gagan.designpatternexample.structural_design_pattern.decorator_pattren.Car;
import com.example.gagan.designpatternexample.structural_design_pattern.decorator_pattren.LuxuryCar;
import com.example.gagan.designpatternexample.structural_design_pattern.decorator_pattren.SportsCar;

/**
 * Created by Gagan on 3/13/2018.
 */

public class DecoratorPattrenClass extends BaseHelperClass {
    @Override
    public void execute(Context context) {
        Car sportsCar = new SportsCar(new BasicCar());
        sportsCar.assemble();
        System.out.println("\n*****");

        Car sportsLuxuryCar = new SportsCar(new LuxuryCar(new BasicCar()));
        sportsLuxuryCar.assemble();
    }

    @Override
    public String getUrl() {
        return "https://cdn.journaldev.com/wp-content/uploads/2013/07/decorator-pattern.png";
    }
}
