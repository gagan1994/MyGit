package com.example.gagan.designpatternexample.behavioural_design_pattern.chain_of_responsibility;

/**
 * Created by Gagan on 3/13/2018.
 */

public class Currency {
    private int amount;

    public Currency(int amt){
        this.amount=amt;
    }

    public int getAmount(){
        return this.amount;
    }
}
