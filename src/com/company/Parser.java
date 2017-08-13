package com.company;

import java.util.Stack;

public class Parser {
    int precTable[] = new int[256];
    Tokenizer t;
    Expression top;

    Parser(String in) {
        t = new Tokenizer(in);
        Stack<Token> tokenStack = new Stack<Token>();
        fillPrecTable();
        Token temp = t.nextToken();
        int currentPrecedence = 0;
        boolean firstOperator = true;
        while (!temp.equals(t.NULLTOKEN)) {
            tokenStack.add(temp);
            temp = t.nextToken();
            if (!temp.equals(t.NULLTOKEN)) {
                if (temp.type == TokenType.OPERATOR) {
                    char op = temp.content.charAt(0);
                    if (firstOperator) {
                        tokenStack.add(temp);
                        temp = t.nextToken();
                        currentPrecedence = precTable[op];
                        firstOperator = false;
                    }
                    else{
                        if (precTable[op] > currentPrecedence) {
                            tokenStack.add(temp);
                            temp = t.nextToken();
                        } else {
                            convertLastExpression(tokenStack, precTable[op]);
                            currentPrecedence = precTable[op];
                            tokenStack.add(temp);
                            temp = t.nextToken();
//                            if (!temp.equals(t.NULLTOKEN)) {
//                                tokenStack.add(temp);
//                            } else {
//                                System.err.println("no next token");
//                            }
                        }
                    }
                } else {
                    System.err.println("next token not operator!");
                }
            }
        }
        convertLastExpression(tokenStack, 1);
        Stack<Token> flippedTokenStack = new Stack<Token>();
        int size = tokenStack.size();
        for (int i = 0; i < size; i++) {
            flippedTokenStack.push(tokenStack.pop());
        }


        top = consolidate(flippedTokenStack);
    }

    private Expression consolidate(Stack<Token> flippedTokenStack) {

        Token r = flippedTokenStack.pop();
        Expression right = fillExpression(r);

        if (flippedTokenStack.empty()) {
            return right;
        }

        Token o = flippedTokenStack.pop();
        Token l = flippedTokenStack.pop();
        Expression left = fillExpression(l);

        flippedTokenStack.push(new Token(fillExpression(left,o,right)));
        return consolidate(flippedTokenStack);
    }

    private void convertLastExpression(Stack<Token> tokenStack, int inputPrecedence) {
        Token r = tokenStack.pop();
        Token o = tokenStack.pop();
        Token l = tokenStack.pop();
        Expression right = fillExpression(r);
        Expression left = fillExpression(l);

        Expression total = fillExpression(left,o,right);

        addTotal(tokenStack, total,inputPrecedence);
    }

    private void convertLastExpression(Stack<Token> tokenStack, Expression right, int inputPrecedence) {
        Token o = tokenStack.pop();
        Token l = tokenStack.pop();
        Expression left = fillExpression(l);

        Expression total = fillExpression(left,o,right);

        addTotal(tokenStack, total,inputPrecedence);
    }

    private void addTotal(Stack<Token> tokenStack, Expression total, int inputPrecedence) {
        if (!tokenStack.empty()) {
            Token op = tokenStack.peek();
            if (op.type != TokenType.OPERATOR)
                System.out.println("Error! expected operator, but incorrect type!");
            else if (precTable[op.content.charAt(0)] < inputPrecedence) {
                tokenStack.add(new Token(total));
            }
            else {
                if (!tokenStack.empty()) {
                    if (tokenStack.peek().type == TokenType.EXPRESSION) {
                        tokenStack.add(new Token(total));
                    } else {
                        convertLastExpression(tokenStack, total,inputPrecedence);
                    }
                } else System.out.println("Error! Operator with no left side!");
            }
        } else {
            tokenStack.add(new Token(total));
        }
    }

    private Expression fillExpression(Expression left, Token o, Expression right) {
        if (o.type == TokenType.OPERATOR) {
            char op = o.content.charAt(0);
            switch (op) {
                case '+':
                    return new Addition(left,right);
                case '-':
                    return new Subtraction(left,right);
                case '*':
                    return new Multiplication(left,right);
                case '/':
                    return new Division(left,right);
                case '^':
                    return new Power(left,right);
                default:
                    return new NullExp("Unrecognized operator");
            }
        }
        else return new NullExp("Not operator");
    }

    private Expression fillExpression(Token t) {
        switch (t.type) {
            case CONSTANT:
                return new Constant(Double.parseDouble(t.content));
            case VARIABLE:
                return new Variable(t.content);
            case EXPRESSION:
                return t.e;
        }
        return new NullExp("Not constant or variable");
    }

    public void fillPrecTable() {
        for (int i = 0; i < 256; i++) {
            precTable[i] = 0;
        }
        precTable[42] = 2; // *
        precTable[43] = 1; // +
        precTable[45] = 1; // -
        precTable[47] = 2; // /
        precTable[94] = 3; // ^
        precTable[246]= 2; // division symbol

    }
}
