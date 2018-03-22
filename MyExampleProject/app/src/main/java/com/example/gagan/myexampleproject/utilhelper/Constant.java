package com.example.gagan.myexampleproject.utilhelper;

import android.content.Intent;

import com.example.gagan.myexampleproject.fragments.BasePagerFragment;
import com.example.gagan.myexampleproject.fragments.NotificationFragment;
import com.example.gagan.myexampleproject.fragments.OpenConnectionFragment;
import com.example.gagan.myexampleproject.fragments.RxJavaPaginatorFragment;
import com.example.gagan.myexampleproject.fragments.home.HomeFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.http.HEAD;

/**
 * Created by Gagan on 3/1/2018.
 */

public class Constant {
    public static final String NO_IMAGE_URL = "https://3.bp.blogspot.com/-XB85UD145qE/V5buf22iv2I/AAAAAAAAA1I/8LBmpwNX-rU7ZjzrHOS2b0F_Pj0xqpHIQCLcB/s1600/nia.png";
    public static final String EMPTY_STRING = "Comming soon";
    final static List<BasePagerFragment> fragmentList = new ArrayList<>();
    public static final String USER_URL = "bins/iz23x";
    public static final String API_URL = "weather?";
    public static final String BASE_URL_API_INTERFACE = "https://api.myjson.com/";
    public static final String BASE_URL_OPEN_CONNECTIONs = "https://api.myjson.com/";

    public static final String BASE_URL_WEATHER = "http://api.openweathermap.org/data/2.5/";
    public static final int REQ_PAUSE = 1;
    public static final int REQ_PREV = 2;
    public static final int REQ_NEXT = 3;
    public static String ApiId = "f724c38a7179c63c0e0a2268b403dce9";
    private static final Map<String, String> HEADER=new HashMap<>();
    public static String NotificationString="NotificationString";
    ///?=London

    public static List<BasePagerFragment> getList() {
        if (fragmentList.size() != 0)
            return fragmentList;
        fragmentList.add(new HomeFragment());
        fragmentList.add(new RxJavaPaginatorFragment());
        fragmentList.add(new OpenConnectionFragment());
        fragmentList.add(new NotificationFragment());
        fragmentList.add(new BasePagerFragment());
        return fragmentList;
    }

    public static Map<String, String> getHEADER() {
        HEADER.put("connection","keep-alive");//"connection", "keep-alive"
        HEADER.put("asdasd","keep-asdas");
        return HEADER;
    }

    public static Intent getPlayPauseIntent() {
        Intent intentPrev = new Intent("Notification_Event");
        intentPrev.putExtra(Constant.NotificationString,1);
        return intentPrev;
    }


    public static Intent getPrevIntent() {
        Intent intentPrev = new Intent("Notification_Event");
        intentPrev.putExtra(Constant.NotificationString,0);
        return intentPrev;    }

    public static Intent getNextIntent() {
        Intent intentPrev = new Intent("Notification_Event");
        intentPrev.putExtra(Constant.NotificationString,2);
        return intentPrev;
    }
}
