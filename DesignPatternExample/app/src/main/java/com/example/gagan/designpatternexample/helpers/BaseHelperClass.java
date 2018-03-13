package com.example.gagan.designpatternexample.helpers;

import android.content.Context;
import android.util.Log;

import com.example.gagan.designpatternexample.MainActivity;

/**
 * Created by Gagan on 3/5/2018.
 */

public abstract class BaseHelperClass {
    public abstract void execute(Context context);

    public String getUrl() {
        return "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/2000px-No_image_available.svg.png";
    }

}
