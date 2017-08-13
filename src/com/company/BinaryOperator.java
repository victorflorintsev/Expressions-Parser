package com.company;

/**
 * Created by Victor on 4/15/2017.
 */
abstract class BinaryOperator extends Expression {

    protected final Expression left;
    protected final Expression right;
    protected abstract int getPrecedence();

    protected BinaryOperator(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    protected String getString(int inputPrecedence) { // left associative code
        if (inputPrecedence > getPrecedence()) return "("+toString()+")";
        else return toString();
    }


    //protected boolean leftAssoc; // boolean whether the operator is left associative or not
}
