package com.example.gagan.designpatternexample.creational_design_patterns.factorydesign;

/**
 * Created by Gagan on 3/5/2018.
 */

public abstract class Computer {
    abstract String getRAM();

    abstract String getHDD();

    abstract String getCPU();

    @Override
    public String toString() {
        return "CPU :" + getCPU() + " HDD: " + getHDD() + " RAM: " + getRAM();
    }

}
