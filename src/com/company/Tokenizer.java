package com.company;

import java.util.ArrayList;

/*
change states, there should be 2 states per token, beginning and continuing,
when it stops continuing then you generate a token class and add it to the arraylist

also, the last state thing needs to go away, states shouldn't worry about the last state.

the table is wrong, it should be a matrix with the 1D table and the current state to resolve into the next,
but dont make one for this that's overkill.

in the expression part of this project:
do everything without toString. then implement it.
fix the test case dad created.

make states an enum
 */

// TOKENIZER NOT GETTING LAST 2 DIGITS!

public class Tokenizer
{
    private int current_token = 0;

    public final Token NULLTOKEN = new Token(TokenType.CONSTANT,"null");
    public Token nextToken() {
        if (current_token >= tokens.size())
            return NULLTOKEN;
        else
            return tokens.get(current_token++);
    }

    public States[] table = new States[256];

    ArrayList<Token> tokens = new ArrayList<Token>();
    Tokenizer(String in) {
        fillTable();
        States state;

        int start_index = 0;
        state = table[in.charAt(0)];

        char cur_char;
        char next_char;
        States temp;
        for (int curIndex = 0; curIndex < in.length(); curIndex++) {
            cur_char = in.charAt(curIndex);
            if (curIndex+1 < in.length())
                next_char = in.charAt(curIndex+1);
            else
                next_char = 0;

            switch (state) {
                case OPERATOR:
                    tokens.add(new Token(TokenType.OPERATOR, Character.toString(in.charAt(curIndex))));
                    state = nextState(in,next_char);
                    break;

                case IGNORE: //do nothing
                    break;

                case START_VARIABLE:
                    temp = table[next_char];
                    if (temp == States.START_CONSTANT || temp == States.START_VARIABLE) {
                        // if next char is not alphanumerical or a '.'
                        start_index = curIndex;
                        state = States.CONT_VARIABLE;
                    }
                    else
                    { // end it and create a token
                        tokens.add(new Token(TokenType.VARIABLE, Character.toString(in.charAt(curIndex))));
                        state = temp;
                    }
                    break;

                case CONT_VARIABLE:
                    temp = table[next_char];
                    if (temp != States.START_CONSTANT && temp != States.START_VARIABLE) {
                        tokens.add(new Token(TokenType.VARIABLE, in.substring(start_index, curIndex + 1)));
                        state = nextState(in,next_char);
                    }
                    break;

                case START_CONSTANT:
                    temp = table[next_char];
                    if (temp == States.START_CONSTANT) {
                        // if next char is not alphanumerical or a '.'
                        start_index = curIndex;
                        state = States.CONT_CONSTANT;
                    }
                    else
                    { // end it and create a token
                        tokens.add(new Token(TokenType.CONSTANT, Character.toString(in.charAt(curIndex))));
                        state = temp;
                    }
                    break;

                case CONT_CONSTANT:
                    temp = table[next_char];
                    if (temp != States.START_CONSTANT) {
                        tokens.add(new Token(TokenType.CONSTANT, in.substring(start_index, curIndex + 1)));
                        state = nextState(in,next_char);
                    }
                    break;

                case OPEN_PARENTHESIS:
                    tokens.add(new Token(TokenType.OPEN_PARENTHESIS, "("));
                    state = nextState(in,next_char);
                    break;

                case CLOSED_PARENTHESIS:
                    tokens.add(new Token(TokenType.CLOSED_PARENTHESIS, ")"));
                    state = nextState(in,next_char);
                    break;

                default:
                    System.err.println("default switch case accessed!");
                    break;
            }
        }
    }

    private States nextState(String in, char c) {
        return table[c];
    }

    private void fillTable() {

        for (int i = 0; i < 256; i++) {
            table[i] = States.IGNORE; // everything else
        }
        for (int i = 42; i <= 47; i++) {
            table[i] = States.OPERATOR; // operator
        }
        for (int i = 65; i <= 90; i++) {
            table[i] = States.START_VARIABLE;      // uppercase letters
            table[i+32] = States.START_VARIABLE; // lowercase
        }
        for (int i = 48; i <= 57 ; i++) {
            table[i] = States.START_CONSTANT; // numbers
        }
        table[94]  = States.OPERATOR; // '^'
        table[46]  = States.START_CONSTANT; // '.' dot now a number
        table[40]  = States.OPEN_PARENTHESIS; // (
        table[91]  = States.OPEN_PARENTHESIS; // [
        table[123] = States.OPEN_PARENTHESIS; // {
        table[41]  = States.CLOSED_PARENTHESIS; // )
        table[93]  = States.CLOSED_PARENTHESIS; // ]
        table[125] = States.CLOSED_PARENTHESIS; // }


    }

    @Override
    public String toString() {
        String out = "";
        for (Token t:tokens) {
            out = out + t.content;
        }
        return out;
    }
}

 /*
    state 0 : operator
    state 1 : Anything Else
    state 2 : Alphabetical
    state 3 : Number
    state 3 : '.'
    state 4 : '-'
    state 5 : open parenthesis
    state 6 : closed parenthesis
    state 7 : exit
 */