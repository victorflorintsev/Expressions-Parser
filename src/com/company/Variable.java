package com.company;

/**
 * Created by Victor on 4/15/2017.
 */
public class Variable extends Expression {
    double compute() {
        return 0;
    }

    @Override
    String getString(int inputPrecedence) {
        return name;
    }

    @Override
    boolean isNull() {
        return false;
    }

    String name;

    Variable(String n) {
        this.name = n;
    }
}
