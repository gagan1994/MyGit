package com.example.gagan.designpatternexample.creational_design_patterns.factorydesign;

/**
 * Created by Gagan on 3/5/2018.
 */

public class Server extends Computer {
    private String RAM;
    private String HDD;
    private String CPU;

    public Server(String ram, String hdd, String cpu) {
        RAM = ram;
        HDD = hdd;
        CPU = cpu;
    }

    @Override
    String getRAM() {
        return RAM;
    }

    @Override
    String getHDD() {
        return HDD;
    }

    @Override
    String getCPU() {
        return CPU;
    }
}
