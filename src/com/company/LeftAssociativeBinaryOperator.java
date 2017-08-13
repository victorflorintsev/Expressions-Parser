package com.company;

public abstract class LeftAssociativeBinaryOperator extends BinaryOperator {
    protected LeftAssociativeBinaryOperator(Expression left, Expression right) {
        super(left, right);
    }
    public String toString(char c) {
        return left.getString(getPrecedence())+c+right.getString(getPrecedence()+1);
    }
}
