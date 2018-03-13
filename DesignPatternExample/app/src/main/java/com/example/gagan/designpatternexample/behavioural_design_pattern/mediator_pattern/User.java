package com.example.gagan.designpatternexample.behavioural_design_pattern.mediator_pattern;

/**
 * Created by Gagan on 3/13/2018.
 */

public abstract class User {
    protected ChatMediator mediator;
    protected String name;

    public User(ChatMediator med, String name){
        this.mediator=med;
        this.name=name;
    }

    public abstract void send(String msg);

    public abstract void receive(String msg);
}