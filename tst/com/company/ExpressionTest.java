package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpressionTest {
    Variable a = new Variable("a");
    Variable b = new Variable("b");
    Variable c = new Variable("c");
    Variable d = new Variable("d");
    @Test
    public void myTest() {

        Addition add1 = new Addition(a,b);
        Power pow1 = new Power(add1,c);

        String something = pow1.toString();
        assertEquals("(a+b)^c",something);
    }
    @Test
    public void myTest2() {

        Addition add1 = new Addition(a,b);
        Subtraction sub1 = new Subtraction(add1,c);

        String something = sub1.toString();
        assertEquals("a+b-c",something);
    }
    @Test
    public void myTest3() {

        Addition add1 = new Addition(b,c);
        Subtraction sub1 = new Subtraction(a,add1);

        String something = sub1.toString();
        assertEquals("a-(b+c)",something);
    }
    @Test
    public void geneTest() {

        Multiplication mul = new Multiplication(a,b);
        Addition add1 = new Addition(c,mul);

        String something = add1.toString();
        assertEquals("c+a*b",something);
    }
    @Test
    public void powerTest() {
        Power pwr = new Power(a,b);
        Subtraction sub = new Subtraction(pwr,c);


        String something = sub.toString();
        assertEquals("a^b-c",something);
    }
    @Test
    public void reversePowerTest() {
        Subtraction sub = new Subtraction(a,b);
        Power pwr = new Power(sub,c);


        String something = pwr.toString();
        assertEquals("(a-b)^c",something);
    }
    @Test
    public void myTest4() {

        Addition add1 = new Addition(b,c);
        Addition add2 = new Addition(a,d);
        Division div1 = new Division(add1,add2);

        String something = div1.toString();
        assertEquals("(b+c)/(a+d)",something);
    }
}
