package com.example.gagan.designpatternexample.behavioural_design_pattern;

import android.content.Context;
import android.util.Log;

import com.example.gagan.designpatternexample.behavioural_design_pattern.chain_of_responsibility.Currency;
import com.example.gagan.designpatternexample.behavioural_design_pattern.chain_of_responsibility.DispenseChain;
import com.example.gagan.designpatternexample.behavioural_design_pattern.chain_of_responsibility.Dollar10Dispenser;
import com.example.gagan.designpatternexample.behavioural_design_pattern.chain_of_responsibility.Dollar20Dispenser;
import com.example.gagan.designpatternexample.behavioural_design_pattern.chain_of_responsibility.Dollar50Dispenser;
import com.example.gagan.designpatternexample.helpers.BaseHelperClass;

import java.util.Scanner;

/**
 * Created by Gagan on 3/13/2018.
 */

public class ChainOfResponsibilityPattern extends BaseHelperClass {

    private final DispenseChain c1;

    public ChainOfResponsibilityPattern() {
        this.c1 = new Dollar50Dispenser();
        DispenseChain c2 = new Dollar20Dispenser();
        DispenseChain c3 = new Dollar10Dispenser();

        // set the chain of responsibility
        c1.setNextChain(c2);
        c2.setNextChain(c3);
    }

    @Override
    public void execute(Context context) {
        while (true) {
            int amount = 0;
            display("Enter amount to dispense");
            Scanner input = new Scanner(System.in);
            amount = input.nextInt();
            if (amount % 10 != 0) {
                display("Amount should be in multiple of 10s.");
                return;
            }
            c1.dispense(new Currency(amount));
        }
    }

    @Override
    public String getUrl() {
        return "";
    }

    public static void display(String mess) {
        Log.w("ChainOfRespons", mess);
    }

}
