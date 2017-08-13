package com.company;

/**
 * Created by Victor on 4/15/2017.
 */
public class Multiplication extends LeftAssociativeBinaryOperator {
    protected Multiplication(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    double compute() {
        return (left.compute() * right.compute());
    }

    @Override
    boolean isNull() {
        return false;
    }

    public int getPrecedence() {
        return 2;
    }

    public String toString() {
        return super.toString('*');
    }

}
