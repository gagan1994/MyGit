package com.example.gagan.designpatternexample.creational_design_patterns.prototypedesignpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gagan on 3/5/2018.
 */

public class Employees implements Cloneable {
    private List<String> list;

    public Employees() {
        list = new ArrayList<String>();
    }

    public Employees(List<String> list) {
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        List<String> temp = new ArrayList<String>(this.getList());

        return new Employees(temp);
    }

    public void loadData() {
        list.add("Pankaj");
        list.add("Raj");
        list.add("David");
        list.add("Lisa");
    }
}
