package com.example.gagan.designpatternexample.structural_design_pattern.decorator_pattren;

import android.support.annotation.CallSuper;

/**
 * Created by Gagan on 3/13/2018.
 */

public class CarDecorator implements Car {

    protected Car car;

    public CarDecorator(Car c){
        this.car=c;
    }
  //  @CallSuper
    @Override
    public void assemble() {
        this.car.assemble();
    }

}