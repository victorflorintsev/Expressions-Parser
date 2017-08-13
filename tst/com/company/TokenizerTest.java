package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TokenizerTest {
    @Test
    public void testTokenizer1() {
        Tokenizer t = new Tokenizer("3+3");
        String something = t.toString();
        assertEquals("3+3",something);
    }

    @Test
    public void testTokenizer2() {
        Tokenizer t = new Tokenizer("3+3+(3*7)/45.2+Abc");
        String something = t.toString();
        assertEquals("3+3+(3*7)/45.2+Abc",something);
    }
}
