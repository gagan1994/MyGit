package com.example.gagan.designpatternexample.creational_design_patterns.builderpattern;

import java.security.PublicKey;

/**
 * Created by Gagan on 3/5/2018.
 */

public class Computer {
    private String HDD;
    private String CPU;
    private boolean isBluetoothEnabled;
    private boolean isGraphicsCardEnabled;

    public boolean isBluetoothEnabled() {
        return isBluetoothEnabled;
    }

    public void setBluetoothEnabled(boolean bluetoothEnabled) {
        isBluetoothEnabled = bluetoothEnabled;
    }

    public boolean isGraphicsCardEnabled() {
        return isGraphicsCardEnabled;
    }

    public void setGraphicsCardEnabled(boolean graphicsCardEnabled) {
        isGraphicsCardEnabled = graphicsCardEnabled;
    }

    public String getHDD() {
        return HDD;
    }

    public String getCPU() {
        return CPU;
    }

    private Computer(ComputerBuilder builder) {
        HDD = builder.getHDD();
        CPU = builder.getCPU();
        this.isBluetoothEnabled = builder.isBluetoothEnabled();
        this.isGraphicsCardEnabled = builder.isGraphicsCardEnabled();
    }

    @Override
    public String toString() {
        String res = "CPU: " + getCPU() + " HDD: " + getHDD();
        res = res + (this.isBluetoothEnabled() ? " with bluetooth " : " without bluetooth ");
        res = res + (this.isGraphicsCardEnabled() ? " with graphic card."
                : " without graphic card.");
        return res;
    }

    public static class ComputerBuilder {
        private String CPU;
        private String HDD;
        private boolean isBluetoothEnabled;
        private boolean isGraphicsCardEnabled;

        public ComputerBuilder(String hdd, String cpu) {
            this.HDD = hdd;
            this.CPU = cpu;
        }

        private String getCPU() {
            return CPU;
        }

        private String getHDD() {
            return HDD;
        }

        private boolean isBluetoothEnabled() {
            return isBluetoothEnabled;
        }


        private boolean isGraphicsCardEnabled() {
            return isGraphicsCardEnabled;
        }


        public ComputerBuilder isBluetoothEnabled(boolean isBluetoothEnbled) {
            this.isBluetoothEnabled = isBluetoothEnbled;
            return this;
        }

        public ComputerBuilder isGraphicsCardEnabled(boolean isGraphicCardEnabled) {
            this.isGraphicsCardEnabled = isGraphicCardEnabled;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}
