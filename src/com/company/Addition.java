package com.company;

/**
 * Created by Victor on 4/15/2017.
 */
public class Addition extends LeftAssociativeBinaryOperator {
    protected Addition(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    double compute() {
        return (left.compute() + right.compute());
    }

    @Override
    boolean isNull() {
        return false;
    }

    @Override
    public String toString() {
        return super.toString('+');
    }

    public int getPrecedence() {
        return 1;
    }
}
