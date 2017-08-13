package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

        @Test
        public void myTest() {

            Parser p = new Parser("3+5*7^3");

            String something = p.top.toString();
            assertEquals("3.0+5.0*7.0^3.0",something);
        }

    @Test
    public void myTest2() {

        Parser p = new Parser("6^3*6+6^7");

        String something = p.top.toString();
        assertEquals("6.0^3.0*6.0+6.0^7.0",something);
    }
}
