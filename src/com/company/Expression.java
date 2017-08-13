package com.company;

/**
 * Created by Victor on 4/15/2017.
 */
public abstract class Expression {
    abstract double compute();
    abstract String getString(int inputPrecedence); // gives you the string with parenthesis if needed given input precedence
    abstract boolean isNull();
}
