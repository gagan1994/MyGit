package com.example.gagan.designpatternexample.creational_design_patterns;

import android.content.Context;
import android.util.Log;

import com.example.gagan.designpatternexample.creational_design_patterns.prototypedesignpattern.Employees;
import com.example.gagan.designpatternexample.helpers.BaseHelperClass;

/**
 * Created by Gagan on 3/5/2018.
 */

public class PrototypeDesignPattern extends BaseHelperClass {
    @Override
    public void execute(Context context) {
        try {
            Employees employees = new Employees();
            employees.loadData();
            showMessage("b Size of employees: " + employees.getList().size());
            Employees employees1 = (Employees) employees.clone();

            showMessage("b Size of employees1: " + employees1.getList().size());

            Employees employees2 = (Employees) employees.clone();
            showMessage("b Size of employees2: " + employees2.getList().size());

            employees2.getList().remove(1);
            employees1.getList().add("Gagan");

            showMessage("a inserting Size of employees1: " + employees1.getList().size());
            showMessage("a removing Size of employees2: " + employees2.getList().size());

        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }

    }

    private void showMessage(String s) {
        Log.w("PrototypeDesignPattern", s);
    }
}
