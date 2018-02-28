package com.example.mylibrary;

/**
 * Created by Gagan on 2/21/2018.
 */

public class CustomThrmoMeter {
    public static float ToDegreeCelsius(float Fahrenheit) {
        float celsius = 5 * (Fahrenheit - 32) / 9;
        return celsius;
    }

    public static float ToFahrenheit(float Celsius) {
        float farenheit = Celsius * (9 / 5) + 32;
        return farenheit;
    }
}
