package com.example.gagan.designpatternexample.behavioural_design_pattern.chain_of_responsibility;

/**
 * Created by Gagan on 3/13/2018.
 */

public interface DispenseChain {
    void setNextChain(DispenseChain nextChain);

    void dispense(Currency cur);
}
