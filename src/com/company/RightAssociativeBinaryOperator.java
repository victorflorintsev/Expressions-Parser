package com.company;

public abstract class RightAssociativeBinaryOperator extends BinaryOperator {

    protected RightAssociativeBinaryOperator(Expression left, Expression right) {
        super(left, right);
    }

    public String toString(char c) {
        return left.getString(getPrecedence()+1)+c+right.getString(getPrecedence());
    }
}
