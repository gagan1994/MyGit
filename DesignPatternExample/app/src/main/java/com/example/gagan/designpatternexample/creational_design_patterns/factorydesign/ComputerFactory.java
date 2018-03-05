package com.example.gagan.designpatternexample.creational_design_patterns.factorydesign;

/**
 * Created by Gagan on 3/5/2018.
 */

public class ComputerFactory {
    public static Computer getComputer(Class type, String ram, String hdd, String cpu) {
        if (type == PC.class) return new PC(ram, hdd, cpu);
        else if (type == Server.class) return new Server(ram, hdd, cpu);
        return null;
    }
}
