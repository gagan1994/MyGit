package com.example.gagan.myexampleproject.utilhelper;

import com.example.gagan.myexampleproject.fragments.BasePagerFragment;
import com.example.gagan.myexampleproject.fragments.RxJavaPaginatorFragment;
import com.example.gagan.myexampleproject.fragments.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gagan on 3/1/2018.
 */

public class Constant {
    public static final String NO_IMAGE_URL = "https://3.bp.blogspot.com/-XB85UD145qE/V5buf22iv2I/AAAAAAAAA1I/8LBmpwNX-rU7ZjzrHOS2b0F_Pj0xqpHIQCLcB/s1600/nia.png";
    public static final String EMPTY_STRING = "Comming soon";
    final static List<BasePagerFragment> fragmentList = new ArrayList<>();
    public static final String USER_URL = "iz23x";
    public static final String API_URL = "weather?";

    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    public static String ApiId="f724c38a7179c63c0e0a2268b403dce9";
    ///?=London

    public static List<BasePagerFragment> getList() {
        if (fragmentList.size() != 0)
            return fragmentList;
        fragmentList.add(new HomeFragment());
        fragmentList.add(new RxJavaPaginatorFragment());
        fragmentList.add(new BasePagerFragment());
        return fragmentList;
    }
}
