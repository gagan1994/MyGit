package com.example.gagan.designpatternexample.creational_design_patterns.factorydesign;

import android.content.Context;
import android.util.Log;

import com.example.gagan.designpatternexample.helpers.BaseHelperClass;

/**
 * Created by Gagan on 3/5/2018.
 */

public class FactoryDesignPatternClass extends BaseHelperClass {

    @Override
    public void execute(Context context) {
        PC pc = (PC) ComputerFactory.getComputer(PC.class, "2 GB",
                "500 GB", "2.4 GHz");
        Server server = (Server) ComputerFactory.getComputer(Server.class,
                "16 GB", "1 TB", "2.9 GHz");

        Log.w("FactoryDesignPattern", "PC: " + pc.toString());

        Log.w("FactoryDesignPattern", "Server: " + server.toString());

    }

    @Override
    public String getUrl() {
        return "https://cdn.journaldev.com/wp-content/uploads/2013/05/factory-pattern-java.png";
    }
}
