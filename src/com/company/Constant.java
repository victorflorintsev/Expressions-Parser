package com.company;

/**
 * Created by Victor on 4/15/2017.
 */
public class Constant extends Expression{

    private final double val;

    @Override
    double compute() {
        return val;
    }

    @Override
    String getString(int inputPrecedence) {
        return toString();
    }

    @Override
    boolean isNull() {
        return false;
    }

    public Constant(double v) {
        val = v;
    }

    @Override
    public String toString() {
        return Double.toString(val);
    }
}
