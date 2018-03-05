package com.example.gagan.designpatternexample.creational_design_patterns;

import android.content.Context;
import android.util.Log;

import com.example.gagan.designpatternexample.helpers.BaseHelperClass;

import java.lang.reflect.Constructor;

/**
 * Created by Gagan on 3/5/2018.
 */

public class SingletonClassExample {
    private static SingletonClassExample _instanceLazyInitializedSingleton;
    private static SingletonClassExample _instance;
    private static SingletonClassExample _instanceEagerInitializedSingleton =
            new SingletonClassExample();
    private static SingletonClassExample _instanceStaticBlock;


    //Eagerly initilized singleton instance
    public static SingletonClassExample getEagerInitializedSingleton() {
        return _instanceEagerInitializedSingleton;
    }
    // static block initialization for exception handling

    static {
        try {
            _instanceStaticBlock = new SingletonClassExample();
        } catch (Exception e) {
            throw new RuntimeException("Exception occured in creating singleton instance");
        }
    }

    //LazyInitializedSingleton initilized only when its called
    public static SingletonClassExample getLazyInitializedSingleton() {
        if (_instanceLazyInitializedSingleton == null)
            _instanceLazyInitializedSingleton = new SingletonClassExample();
        return _instanceLazyInitializedSingleton;
    }

    //ThreadSafe
    public static synchronized SingletonClassExample getThreadSafeInstance() {

        if (_instance == null)
            _instance = new SingletonClassExample();

        return _instance;
    }


    private SingletonClassExample() {

    }

    public static class SingleTonHelperClass extends BaseHelperClass {

        @Override
        public void execute(Context context) {
            //Reflection to destroy Singleton Pattern
            SingletonClassExample firstSingletonRef = SingletonClassExample.getThreadSafeInstance();
            SingletonClassExample secondSingletonRef = null;
            try {
                Constructor[] constructors = SingletonClassExample.class.getDeclaredConstructors();
                for (Constructor constructor : constructors) {
                    //Below code will destroy the singleton pattern
                    constructor.setAccessible(true);
                    secondSingletonRef = (SingletonClassExample) constructor.newInstance();
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //BothAreDiferent();
            Log.w("SingleTonClass", "first instance" + firstSingletonRef.hashCode());
            Log.w("SingleTonClass", "second instance" + secondSingletonRef.hashCode());
        }

    }
}
