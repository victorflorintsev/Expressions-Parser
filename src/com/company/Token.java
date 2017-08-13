package com.company;

enum TokenType {
    OPERATOR, VARIABLE, CONSTANT, OPEN_PARENTHESIS, CLOSED_PARENTHESIS, EXPRESSION
}

public class Token {
    TokenType type;
    String content;
    Expression e = new NullExp("No expression in this token");

    Token(TokenType type, String content) {
        this.type    = type;
        this.content = content;
    }

    Token(Expression in) {
        e = in;
        type = TokenType.EXPRESSION;
        content = e.toString();
    }

    boolean equals(Token comparesTo) {
        return comparesTo.content == content;
    }
}
