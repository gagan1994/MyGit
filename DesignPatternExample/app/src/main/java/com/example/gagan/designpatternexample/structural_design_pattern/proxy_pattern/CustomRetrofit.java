package com.example.gagan.designpatternexample.structural_design_pattern.proxy_pattern;

import com.example.gagan.designpatternexample.structural_design_pattern.ProxyPattern;

/**
 * Created by Gagan on 3/6/2018.
 */

public class CustomRetrofit implements InternetConnection {
    @Override
    public void connectTo(String url) throws Exception {
        ProxyPattern.display("connecting to " + url + " ...");
    }
}
