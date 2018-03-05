package com.example.gagan.designpatternexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.gagan.designpatternexample.creational_design_patterns.BuilderPatternClasss;
import com.example.gagan.designpatternexample.creational_design_patterns.PrototypeDesignPattern;
import com.example.gagan.designpatternexample.creational_design_patterns.SingletonClassExample;
import com.example.gagan.designpatternexample.creational_design_patterns.factorydesign.FactoryDesignPatternClass;
import com.example.gagan.designpatternexample.helpers.BaseHelperClass;

import java.lang.reflect.Constructor;

public class MainActivity extends AppCompatActivity {
    private BaseHelperClass helperClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helperClass = new PrototypeDesignPattern();
        helperClass.execute(this);
    }

    //Reflection to destroy Singleton Pattern


}
