package com.example.gagan.cloths.utils;

import com.example.gagan.cloths.pojo.Cloths;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gagan on 5/3/2018.
 */

public class Utils {
    public static List<Cloths> getDummyCloths() {
        List<Cloths> cloths = new ArrayList<>();
        for (int i = 0; i < 20; i++)
            cloths.add(Cloths.getRandom());
        return cloths;
    }
}
