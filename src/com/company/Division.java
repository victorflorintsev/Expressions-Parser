package com.company;

/**
 * Created by Victor on 4/15/2017.
 */
public class Division extends LeftAssociativeBinaryOperator {
    @Override
    double compute() {
        return (left.compute() / right.compute());
    }

    @Override
    boolean isNull() {
        return false;
    }

    protected boolean leftAssoc = true;

    @Override
    public int getPrecedence() {
        return 2;
    }

    public String toString() {
        return super.toString('/');
    }


    public Division(Expression left, Expression right) {
        super(left, right);
        //this.leftAssoc = leftAssoc;
    }
}
