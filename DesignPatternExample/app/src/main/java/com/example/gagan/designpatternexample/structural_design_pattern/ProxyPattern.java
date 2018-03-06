package com.example.gagan.designpatternexample.structural_design_pattern;

import android.content.Context;
import android.util.Log;

import com.example.gagan.designpatternexample.helpers.BaseHelperClass;
import com.example.gagan.designpatternexample.structural_design_pattern.proxy_pattern.RetrofitProxy;

/**
 * Created by Gagan on 3/6/2018.
 */

public class ProxyPattern extends BaseHelperClass {
    @Override
    public void execute(Context context) {
        RetrofitProxy proxy=new RetrofitProxy();
        try {
            proxy.connectTo("abcd.com");
        } catch (Exception e) {
           display(e.getMessage());
        }
        try {
            proxy.connectTo("abc.com");
        } catch (Exception e) {
            display(e.getMessage());
        }

    }

    public static void display(String s) {
        Log.w("ProxyPattern", s);
    }
}
