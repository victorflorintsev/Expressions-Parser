package com.company;

public class NullExp extends Expression {
    @Override
    double compute() {
        return 1;
    }

    String why = "NULL";
    NullExp(String in) {
        why = in;
    }
    @Override
    String getString(int inputPrecedence) {
        return why;
    }

    @Override
    boolean isNull() {
        return true;
    }
}
