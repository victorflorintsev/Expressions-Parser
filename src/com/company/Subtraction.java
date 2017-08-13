package com.company;

/**
 * Created by Victor on 4/15/2017.
 */
public class Subtraction extends LeftAssociativeBinaryOperator {
    protected Subtraction(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    double compute() {
        return (left.compute() - right.compute());
    }

    @Override
    boolean isNull() {
        return false;
    }

    protected boolean leftAssoc = true;

    public int getPrecedence() {
        return 1;
    }

    public String toString() {
        return super.toString('-');
    }
}
