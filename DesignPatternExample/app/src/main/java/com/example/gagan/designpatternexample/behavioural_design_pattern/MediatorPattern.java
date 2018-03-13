package com.example.gagan.designpatternexample.behavioural_design_pattern;

import android.content.Context;
import android.util.Log;

import com.example.gagan.designpatternexample.behavioural_design_pattern.mediator_pattern.ChatMediator;
import com.example.gagan.designpatternexample.behavioural_design_pattern.mediator_pattern.ChatMediatorImpl;
import com.example.gagan.designpatternexample.behavioural_design_pattern.mediator_pattern.User;
import com.example.gagan.designpatternexample.behavioural_design_pattern.mediator_pattern.UserImpl;
import com.example.gagan.designpatternexample.helpers.BaseHelperClass;

/**
 * Created by Gagan on 3/13/2018.
 */

public class MediatorPattern extends BaseHelperClass {
    @Override
    public void execute(Context context) {
        ChatMediator mediator = new ChatMediatorImpl();
        User user1 = new UserImpl(mediator, "Pankaj");
        User user2 = new UserImpl(mediator, "Lisa");
        User user3 = new UserImpl(mediator, "Saurabh");
        User user4 = new UserImpl(mediator, "David");

        mediator.addUser(user1);
        mediator.addUser(user2);
        mediator.addUser(user3);
        mediator.addUser(user4);

        user1.send("Hi All");
    }

    @Override
    public String getUrl() {
        return "https://cdn.journaldev.com/wp-content/uploads/2013/07/mediator-pattern.png";
    }

    public static void display(String mess) {
        Log.w("MediatorPattern", mess);
    }

}
