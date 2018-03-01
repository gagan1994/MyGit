package com.example.gagan.myexampleproject.utilhelper;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by Gagan on 3/1/2018.
 */

public class Utils {
    public static String checkNull(String item) {
        return TextUtils.isEmpty(item) ? Constant.EMPTY_STRING : item;
    }

    public static String checkImageNull(String item) {
        return TextUtils.isEmpty(item) ?
                Constant.NO_IMAGE_URL
                : item;
    }

    public static void Toast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
