package com.example.gagan.designpatternexample.structural_design_pattern.decorator_pattren;

/**
 * Created by Gagan on 3/13/2018.
 */

public class LuxuryCar extends CarDecorator {

    public LuxuryCar(Car c) {
        super(c);
    }

    @Override
    public void assemble(){
        super.assemble();
        System.out.print(" Adding features of Luxury Car.");
    }
}
