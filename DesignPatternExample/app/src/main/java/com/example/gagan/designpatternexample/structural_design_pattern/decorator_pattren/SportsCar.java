package com.example.gagan.designpatternexample.structural_design_pattern.decorator_pattren;

/**
 * Created by Gagan on 3/13/2018.
 */

public class SportsCar extends CarDecorator {
    public SportsCar(Car c) {
        super(c);
    }

    @Override
    public void assemble() {
        super.assemble();
        System.out.print(" Adding features of Sports Car.");
    }
}
