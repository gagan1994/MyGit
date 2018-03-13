package com.example.gagan.designpatternexample.behavioural_design_pattern.mediator_pattern;

/**
 * Created by Gagan on 3/13/2018.
 */

public interface ChatMediator {

    public void sendMessage(String msg, User user);

    void addUser(User user);
}