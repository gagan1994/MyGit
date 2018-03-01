package com.example.gagan.myexampleproject.utilhelper;

import com.example.gagan.myexampleproject.fragments.BasePagerFragment;
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
    public static final String BASE_URL = "https://api.myjson.com/bins/";
    public static List<BasePagerFragment> getList() {
        if (fragmentList.size() != 0)
            return fragmentList;
        fragmentList.add(new HomeFragment());
        fragmentList.add(new BasePagerFragment());
        return fragmentList;
    }
}
