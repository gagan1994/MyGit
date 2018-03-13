package com.example.gagan.designpatternexample.behavioural_design_pattern.mediator_pattern;

import com.example.gagan.designpatternexample.behavioural_design_pattern.MediatorPattern;

/**
 * Created by Gagan on 3/13/2018.
 */

public class UserImpl extends User {
    public UserImpl(ChatMediator med, String name) {
        super(med, name);
    }

    @Override
    public void send(String msg) {
        MediatorPattern.display(this.name + ": Sending Message=" + msg);
        mediator.sendMessage(msg, this);
    }

    @Override
    public void receive(String msg) {
        MediatorPattern.display(this.name + ": Received Message:" + msg);
    }

}
