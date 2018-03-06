package com.example.gagan.designpatternexample.structural_design_pattern.proxy_pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gagan on 3/6/2018.
 */

public class RetrofitProxy implements InternetConnection {
    private static List<String> bannedUrl = new ArrayList<>();
    private CustomRetrofit retrofit = new CustomRetrofit();

    static {
        bannedUrl.add("abc.com");
        bannedUrl.add("def.com");
    }

    @Override
    public void connectTo(String url) throws Exception {
        if (bannedUrl.contains(url))
            throw new Exception("Access denaied");
        retrofit.connectTo(url);
    }
}
