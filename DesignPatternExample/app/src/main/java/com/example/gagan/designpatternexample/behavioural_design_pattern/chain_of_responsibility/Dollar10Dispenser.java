package com.example.gagan.designpatternexample.behavioural_design_pattern.chain_of_responsibility;

import com.example.gagan.designpatternexample.behavioural_design_pattern.ChainOfResponsibilityPattern;

/**
 * Created by Gagan on 3/13/2018.
 */

public class Dollar10Dispenser implements DispenseChain {

    private DispenseChain chain;

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.chain = nextChain;
    }

    @Override
    public void dispense(Currency cur) {
        if (cur.getAmount() >= 10) {
            int num = cur.getAmount() / 10;
            int remainder = cur.getAmount() % 10;
            ChainOfResponsibilityPattern.display("Dispensing " + num + " 10$ note");
            if (remainder != 0) this.chain.dispense(new Currency(remainder));
        } else {
            this.chain.dispense(cur);
        }
    }

}