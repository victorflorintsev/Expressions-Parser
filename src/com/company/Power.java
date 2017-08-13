package com.company;

public class Power extends RightAssociativeBinaryOperator {
    protected Power(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    protected int getPrecedence() {
        return 3;
    }

    @Override
    double compute() {
        return Math.pow(left.compute(),right.compute());
    }

    @Override
    boolean isNull() {
        return false;
    }

    @Override
    public String toString() {
        return super.toString('^');
    }
}
