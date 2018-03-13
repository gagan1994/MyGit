package com.example.gagan.designpatternexample.behavioural_design_pattern.chain_of_responsibility;

import com.example.gagan.designpatternexample.behavioural_design_pattern.ChainOfResponsibilityPattern;

/**
 * Created by Gagan on 3/13/2018.
 */

public class Dollar20Dispenser implements DispenseChain {

    private DispenseChain chain;

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.chain = nextChain;
    }

    @Override
    public void dispense(Currency cur) {
        if (cur.getAmount() >= 20) {
            int num = cur.getAmount() / 20;
            int remainder = cur.getAmount() % 20;
            ChainOfResponsibilityPattern.display("Dispensing " + num + " 20$ note");
            if (remainder != 0) this.chain.dispense(new Currency(remainder));
        } else {
            this.chain.dispense(cur);
        }
    }

}
